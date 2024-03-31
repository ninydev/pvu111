<?php

namespace App\Http\Controllers;

use App\Jobs\MoveFileToStorageJob;
use App\Models\FileModel;
use App\Models\UserModel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;

class FileController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth:api');
    }

    public function index(Request $request)
    {
         $files = $request->user()->files()->paginate(10);
         return response()->json($files);
    }

    public function upload(Request $request)
    {
        if(!$request->hasFile('file')) {
            return response()->json(
                ['message' => 'File not found'], 400
            );
        }

        $uploadFile = $request->file('file');
        $path = $request->user()->id . "/" . $uploadFile->getClientOriginalName();

        Storage::disk('for_chunk')->put($path, $uploadFile->getContent());

        $fileModel = new FileModel([
            'owner_id' => $request->user()->id,
            'name' => $uploadFile->getClientOriginalName(),
            'path' => $path,
            'url' => null,
            // Говорит о том, что файлик не залит в хранилище
            'is_upload' => false
        ]);

        $fileModel->save();
        $file_id = $fileModel->id;

        // После того - как по пути у меня есть файл - мне нужно его перенести в хранилище

//        $fileModelToUpload = FileModel::findOrFail($file_id);
//
//        // Вот этот пут проблеммный !!! он может заливаться час
//        Storage::disk('for_files')->put($fileModelToUpload->path, Storage::disk('for_chunk')->get($fileModelToUpload->path));
//        $fileModelToUpload->url = Storage::disk('for_files')->url($fileModelToUpload->path);
//        $fileModelToUpload->is_upload = true;
//        $fileModelToUpload->save();

        // Выполним операцию в фоне
        MoveFileToStorageJob::dispatch($fileModel->id);

        return response()->json([
                'step1' => $fileModel->toArray(),
                'step2' => null
            ]
            , 201);
    }

    public function uploadOne(Request $request)
    {
        if(!$request->hasFile('file')) {
            return response()->json(
                ['message' => 'File not found'], 400
            );
        }

        $file = $request->file('file');
        $path = $request->user()->id . "/" . $file->getClientOriginalName();

        // Оптимальным является выделение корзины в отдельную настройку
        // так удобнее управлять наборами файлов
        Storage::disk('for_files')->put($path, $file->getContent());
        $fileUrl = Storage::disk('for_files')->url($path);

        $fileModel = new FileModel([
            'owner_id' => $request->user()->id,
            'name' => $file->getClientOriginalName(),
            'path' => $path,
            'url' => $fileUrl
        ]);

        $fileModel->save();



        return response()->json($fileModel->toArray(), 201);
    }
}
