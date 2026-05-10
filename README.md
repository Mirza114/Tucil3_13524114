# Tucil3_13524114

# Ice Sliding Puzzle Solver

Program ini dibuat untuk mencari jalur penyelesaian pada permainan Ice Sliding Puzzle. Aktor bergerak dari posisi awal `Z` menuju titik tujuan `O` dengan cara meluncur sampai berhenti sebelum batu `X`.

Program mendukung algoritma:
- UCS
- GBFS
- A*
- BFS sebagai tambahan

## Requirement

- Java JDK 17 atau versi lebih baru

## Struktur Folder

```txt
Tucil3_13524114/
├── src/
├── bin/
├── test/
├── doc/
└── README.md
```

Keterangan:
- `src` berisi source code program.
- `bin` berisi file hasil compile.
- `test` berisi file input dan hasil solusi.
- `doc` berisi laporan dalam bentuk PDF.

## Cara Compile

Jalankan perintah berikut dari folder utama project:

```bash
javac src/*.java -d bin
```

## Cara Menjalankan Program

```bash
java -cp bin Main
```

Setelah program berjalan, masukkan path file input, misalnya:

```txt
test/input1.txt
```

Lalu pilih algoritma:

```txt
UCS
GBFS
A*
BFS
```

Jika memilih `GBFS` atau `A*`, program akan meminta pilihan heuristic:

```txt
H1
H2
H3
```

## Format Input

Contoh isi file input:

```txt
7 7
XXXXXXX
X0****X
X**X**X
X****OX
X1***LX
XZ**X*X
XXXXXXX
999 999 999 999 999 999 999
999 3 5 2 8 1 999
999 7 4 999 6 9 999
999 2 8 3 5 4 999
999 6 1 7 2 999 999
999 9 3 4 999 8 999
999 999 999 999 999 999 999
```

## Output Program

Program akan menampilkan:
- solusi gerakan
- total cost
- waktu eksekusi
- jumlah iterasi
- visualisasi setiap langkah
- pilihan playback
- pilihan menyimpan solusi ke file

## Nama dan NIM

- Nama: Mirza
- NIM: 13524114
