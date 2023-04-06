package com.example.tumbasmas

object ListProduk {
    private val data = arrayOf(
        arrayOf(
            "Mouse Gaming Khas Tuban","Kekuatan dunia ","$20",
            R.drawable.mas
        ),
        arrayOf(
            "Daging Kadal","Cocok untuk aqiqah","$14",R.drawable.ata
        ),
        arrayOf(
            "Lele","Ikan dapat sepeda","$2",R.drawable.iwak
        ),
        arrayOf(
            "Tegar by Meja","Mebel terbaik dari kediri yaitu Tegar Meja Kursi","$100",
            R.drawable.tegar
        ),
        arrayOf(
            "Sikilku","Sepatu yang dapat menghancurkan peradaban dalam sekali",
            "$200",R.drawable.sepat
        ),
        arrayOf(
            "Votre K666","Keyboard tuban",
            "$500",R.drawable.terbaik
        )
    )
    val listData: ArrayList<produk>
        get() {
            val list = arrayListOf<produk>()
            for (aData in data){
                val produk = produk()
                produk.name = aData[0] as String
                produk.description = aData[1] as String
                produk.price = aData[2] as String
                produk.foto = aData[3] as Int
                list.add(produk)
            }
            return list
        }
}