import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  build: {
    outDir: 'dist', // This specifies where the built files will go
    emptyOutDir: true // Ensures the outDir is emptied before the build
  }
});
