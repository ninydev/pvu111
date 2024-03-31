<?php

namespace App\Http\Controllers;

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

        $file = $request->file('file');
        $path = $file->getClientOriginalName();

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
