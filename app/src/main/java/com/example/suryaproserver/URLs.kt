package com.example.suryaproserver

object URLs {

        var ips = "10.244.10.59"

        private val ROOT_URL = "http://$ips/TAsuryapro/registrationapi.php?apicall="
        val URL_REGISTER = ROOT_URL + "signup"
        val URL_LOGIN = ROOT_URL + "login"

        //peternak
        val URL_BIOPETER = "http://$ips/TAsuryapro/query_operasi_bio_peternak.php"
        val URL_BIOPETERVIEW = "http://$ips/TAsuryapro/show_data_bio_peternak.php"
        val URL_ASPEKPETER = "http://$ips/TAsuryapro/query_operasi_aspek_peternak.php"
        val URL_ASPEKPETERVIEW = "http://$ips/TAsuryapro/show_data_aspek_peternak.php"
        val URL_BOBOTASPEK = "http://$ips/TAsuryapro/rekom_aspek_peternak.php"
        val URL_DAFTARMITRA = "http://$ips/TAsuryapro/daftar_peternak.php"

        //mitra
        val URL_BIOMITRA = "http://$ips/TAsuryapro/query_operasi_bio_mitra.php"
        val URL_BIOMITRAVIEW = "http://$ips/TAsuryapro/show_data_bio_mitra.php"
        val URL_ASPEKMITRA = "http://$ips/TAsuryapro/query_operasi_aspek_mitra.php"
        val URL_ASPEKMITRAVIEW = "http://$ips/TAsuryapro/show_data_aspek_mitra.php"
        val URL_BOBOTASPEKM = "http://$ips/TAsuryapro/rekom_aspek_mitra.php"
        val URL_DAFTARPETERNAK = "http://$ips/TAsuryapro/daftar_mitra.php"
    }

