# manajemen-kost
ini adalah workspace kelompok  MID 1A pemrograman berbasis objek dengan teman manajemen kost

# Alur pengumpulan tugas/project

## Requirements:
1. Buat akun GitHub (https://github.com/)
2. Download Git (https://git-scm.com/)

## Alur pengumpulan tugas ke repositori ini:

1. **Fork** repositori ini

2. **Clone** repositori hasil **fork** anda

   ```sh

   git clone https://github.com/MegIv/manajemen-kost.git

   ```

3. Setelah anda **clone**, masuk ke folder hasil **clone** tersebut lalu buat **branch** dengan nama **Fitur-n** dan n = fitur keberapa

   ```sh

   cd manajemen-kost
   git branch Fitur-n
   git checkout Fitur-n
   git config user.name USERNAME_GITHUB
   git config user.email EMAIL_GITHUB

   ```

4. Setelah anda pindah ke **branch** yang telah anda buat, buat sebuah folder dengan nama **FITUR_ANDA** masing-masing dan masuk ke folder tersebut.
   ```sh

   mkdir FITUR_ANDA
   cd FITUR_ANDA

   ```

5. Semua _file_ project masing-masing, disimpan kedalam folder **FITUR_ANDA**
6. Setiap membuat _file_ atau melakukan perubahan, lakukan proses **commit** dengan pesan yang deskriptif

   ```sh
   CATATAN: NAMA FILE TUGAS MENYESUAIKAN DENGAN FUNGSI
   CONTOH: IFilterable.java , PencarianDasar.java
   
   git add . #perintah ini memilih seluruh file sekaligus
   # atau
   git add "FITUR_ANDA/FilePythonYangBerubahAtauDitambahkan.java" #perintah ini memilih file tertentu
   
   SAYA SARANKAN JANGAN PAKAI git add . UNTUK MENGHINDARI PENAMBAHAN UNTUK SEMUA FILE TERMASUK FILE YANG TIDAK DIINGINKAN
   LEBIH BAIK MEMAKAI git add "FITUR_ANDA/nama_file.java" UNTUK MENAMBAHKAN FILE
   
   git status untuk mengecek apakah file sudah ter add atau tidak.
   Jika file yang ingin di add sudah berwarna hijau lanjut ke commit.
   Jika file yang ingin di add berwarna merah lakukan add terlebih dahulu
   
   git commit -m "pesan mengenai penambahan atau perubahan apa yang anda lakukan"
   
   ```

7. file/tugas anda disetujui, **push** seluruh _file_ yang telah anda buat

   ```sh

   # pastikan proses commit telah selesai terhadap setiap file
   git push origin FITUR_ANDA

   ```
   
   Jika sebelumnya anda belum pernah menghubungkan Git di komputer anda dengan akun GitHub anda, maka anda akan diminta untuk mengisi username dan password untuk
   melakukan push ke repo GitHub anda.
   ```sh

   # username = username anda
   # password = persocal access token anda

   ```
   
   Cara membuat personal access token:
   ```sh
   
   #1. Klik profile anda pada pojok kanan atas GitHub
   #2. Pilih menu settings
   #3. Scroll ke bagian bawah dan pilih menu Dveloper settings
   #4. Pilih Prsonal access tokens
   #5. Pilih Generate new tokes
   #6. Tuliskan note untuk token anda (ex: Token for LAB-PBO-03-2025)
   #7. Atur waktu expiration token anda (sesuai keinginan anda)
   #8. Pada select scope, ceklis box repo
   #9. Klik generate new token
   #10. Pastikan untuk meng-copy token anda dan menyimpannya, karena token hanya bisa diliat sekali (*Jika hilang, buat token baru)

   ```
   
8. Masuk ke akun GitHub anda, dan buka repo yang telah anda **fork** dan **clone**. Lihat perubahan yang terjadi pada repo tersebut dan pastikan bahwa tugas yang
   telah anda **push** sesuai dan berada pada repo tersebut.
   
9. Pilih menu **Pull request** dan lakukan **pull request** pada tugas praktikum anda.
10. Berdoa Mko Di ACC, Klau ada kue-nya auto ACC

