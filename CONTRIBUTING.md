# 📚 Panduan GitHub

panduan untuk anggota tim untuk menggunakan github secara lengkap

---

## 🧭 Daftar Isi
1. [🔗 Fork Repositori](#-1-fork-repositori)  
2. [💻 Clone Repo](#-2-clone-repo)  
3. [🌿 Buat Branch](#-3-buat-branch-baru)  
4. [✏️ Edit File](#️-4-edit-file)  
5. [💾 Commit](#-5-commit-perubahan)  
6. [🚀 Push ke GitHub](#-6-push-ke-github)  
7. [🔄 Pull Request](#-7-buat-pull-request)  
8. [🔃 Sinkronisasi Repo](#-8-sinkronisasi-repo)  

---

### 1. 🔗 Fork Repositori
**Salin repo ke akun GitHub Anda**  
```
+ Langkah:
1. Kunjungi repo target (contoh: `github.com/pemilik/repo`)
2. Klik tombol [Fork] di kanan atas
3. Pilih akun GitHub Anda sebagai tujuan
```

### 2. 💻 Clone Repo
**Clone repo ke komputer**

# Jalankan di terminal:
```
git clone https://github.com/akun-anda/repo.git
cd repo
```

### 3. 🌿 Buat Branch Baru
**Buat cabang kerja terpisah**

# Buat dan pindah ke branch baru:
```
git checkout -b fitur-baru
```

💡 Best Practice:

    Beri nama deskriptif (contoh: fix-typo atau add-login-form)

### 4. ✏️ Edit File
**Lakukan perubahan**

    - Buka file di editor favorit (VS Code, Sublime, dll.)
    - Modifikasi konten
    - Simpan perubahan (Ctrl + S)

### 5. 💾 Commit Perubahan
**Simpan perubahan di Git**

# Periksa perubahan:
```
git status
```
# Tambahkan file:
```
git add nama-file.txt  # atau gunakan `git add .` untuk semua file
```
# Buat commit:
```
git commit -m "✨ Menambahkan fitur baru"
```

### 6. � Push ke GitHub
**Unggah ke repositori Anda**
```
git push origin fitur-baru
```

### 7. 🔄 Buat Pull Request
**Ajukan perubahan ke repo asli**

    1. Buka halaman repo di GitHub
    2. Klik [Compare & pull request]
    3. Isi template PR dengan jelas
    4. Tekan [Create pull request]

### 8. 🔃 Sinkronisasi Repo
**Update dengan repo asli**

# Tambahkan remote upstream:
```
git remote add upstream https://github.com/pemilik/repo.git
```
# Tarik perubahan terbaru:
```
git fetch upstream
git merge upstream/main
```

### 📊 Diagram Alur Kerja

    A[Fork Repo] --> B[Clone]
    B --> C[Buat Branch]
    C --> D[Edit File]
    D --> E[Commit]
    E --> F[Push]
    F --> G[Pull Request]
