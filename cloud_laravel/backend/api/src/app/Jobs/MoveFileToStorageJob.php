<?php

namespace App\Jobs;

use App\Models\FileModel;
use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Foundation\Bus\Dispatchable;
use Illuminate\Queue\InteractsWithQueue;
use Illuminate\Queue\SerializesModels;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Storage;

class MoveFileToStorageJob implements ShouldQueue
{
    use Dispatchable, InteractsWithQueue, Queueable, SerializesModels;


    /**
     * Create a new job instance.
     */
    public function __construct(
        private $file_id
    )
    {
        //
    }

    /**
     * Execute the job.
     */
    public function handle(): void
    {
        $fileModelToUpload = FileModel::findOrFail($this->file_id);
        Storage::disk('for_files')->put($fileModelToUpload->path, Storage::disk('for_chunk')->get($fileModelToUpload->path));

        // Тут я могу преобразовать файл в нужный мне формат !!!

        $fileModelToUpload->url = Storage::disk('for_files')->url($fileModelToUpload->path);
        $fileModelToUpload->is_upload = true;
        $fileModelToUpload->save();

        Log::info($fileModelToUpload->toArray());

        Storage::disk('for_chunk')->delete($fileModelToUpload->path);
    }
}
