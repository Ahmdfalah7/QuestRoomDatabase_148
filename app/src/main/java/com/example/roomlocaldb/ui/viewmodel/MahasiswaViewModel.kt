package com.example.RoomLocalDB.ui.theme.Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomlocaldb.data.entity.Mahasiswa
import com.example.roomlocaldb.repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs) : ViewModel(){

    var uiState by mutableStateOf(MhsUIState())
    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM Tidak Boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            jeniskelamin = if (event.jeniskelamin.isNotEmpty()) null else "Jenis Kelamin Tidak Boleh Kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat Tidak Boleh Kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas Tidak Boleh Kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan Tidak Boleh Kosong",
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()


    }

    fun saveData(){
        val currentEvent = uiState.mahasiswaEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState= uiState.copy(
                        snackbarMessage = "Data Berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception){
                    uiState = uiState.copy(
                        snackbarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "Input Tidak valid. Periksa kembali data anda."
            )
        }
    }

    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackbarMessage = null)
    }
}

data class MhsUIState(
    val  mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val  isEntryValid: FormErrorState = FormErrorState(),
    val  snackbarMessage: String? = null
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jeniskelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,
){
    fun isValid(): Boolean{
        return nim == null && nama == null && jeniskelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}



fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jeniskelamin = jeniskelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)

data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jeniskelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)