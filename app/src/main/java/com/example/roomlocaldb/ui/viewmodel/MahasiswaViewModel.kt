package com.example.roomlocaldb.ui.viewmodel

import com.example.roomlocaldb.data.entity.Mahasiswa

class MahasiswaViewModel {
}

data class  MahasiswaEvent(
    val nim : String = "",
    val nama : String = "",
    val jeniskelamin : String = "",
    val alamat : String = "",
    val kelas : String = "",
    val angkatan : String = "",
)
{}

fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jeniskelamin = jeniskelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan,
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
)