import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import WebWorkerPlugin from 'vite-plugin-webworker-service';

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    // Добавьте правило для обработки файлов воркеров
    fs: {
      strict: false,
    },
  },
  plugins: [
    vue(),
    WebWorkerPlugin()
  ],

  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
