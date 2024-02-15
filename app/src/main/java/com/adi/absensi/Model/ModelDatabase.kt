package com.adi.absensi.Model

import androidx.compose.foundation.text.Handle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener

@IgnoreExtraProperties
data class Absensi(
    var nama: String? = "",
    var foto: String? = "",
    var tanggal: String? = "",
    var lokasi: String? = "",
    var keterangan: String? = ""
)

class ModelDatabase {

    private val databaseReference = FirebaseDatabase.getInstance().reference.child("absensi")

    fun tambahAbsensi(absensi: Absensi) {
        val key = databaseReference.push().key
        key?.let {
            databaseReference.child(it).setValue(absensi)
        }
    }
    fun bacaAbsensi(key: String, callback: (Absensi?) -> Unit) {
    databaseReference.child(key).addListenerForSingleValueEvent(object : ValueEventListener {
       override fun onDataChange(snapshot: DataSnapshot) {val absensi = snapshot.getValue(Absensi::class.java)            callback(absensi)
        }

        override fun onCancelled(error: DatabaseError) {
            Handle error
        }
    })
}