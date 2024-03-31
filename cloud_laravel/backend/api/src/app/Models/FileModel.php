<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class FileModel extends Model
{
    use HasFactory;

    // Имя таблицы (если я не следую правилам наименований)
    protected $table="files";

    // Поля - доступные для изменения
    protected $fillable = [
        'path',
        'url',
        'name',
        'owner_id',
        'is_upload'
    ];

    public function owner(): BelongsTo
    {
        return $this->belongsTo(UserModel::class, 'owner_id');
    }
}
