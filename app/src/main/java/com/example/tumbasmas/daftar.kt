package com.example.tumbasmas

import android.app.DatePickerDialog
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.ULocale
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class daftar : Fragment() {

    private var namaInput: String = ""
    private var usernameInput: String = ""
    private var emailInput: String = ""
    private var telpInput: String = ""
    private var alamatInput: String = ""
    private var genderInput: String = ""
    private var ttlInput: String = ""
    private var db: AppDatabase? = null
    private var userDao: UserDao? = null
    @RequiresApi(Build.VERSION_CODES.N)
    private var calendar = Calendar.getInstance()


    private var edtName: EditText? = null
    private var edtUsername: EditText? = null
    private var edtEmail: EditText? = null
    private var edtTelp: EditText? = null
    private var edtAddress: EditText? = null
    private var btn_daftar: Button? = null
    private var spinnerGender: Spinner? = null
    private var edtTTL: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daftar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }

    private fun initLocalDB() {
        db = AppDatabase.getAppDatabase(requireActivity())
        userDao = db?.userDao()
        setDataSpinnerGender()
    }

    private fun setDataSpinnerGender() {
        val adapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.gender_list,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerGender?.adapter = adapter
    }

    private fun validasiInput() {
        namaInput = edtName?.text.toString()
        usernameInput = edtUsername?.text.toString()
        emailInput = edtEmail?.text.toString()
        ttlInput = edtTTL?.text.toString()
        telpInput = edtTelp?.text.toString()
        alamatInput = edtAddress?.text.toString()
        genderInput = spinnerGender?.selectedItem.toString()
        when {
            namaInput.isEmpty() -> edtName?.error = "Nama tidak boleh kosong"

            usernameInput.isEmpty() -> edtUsername?.error = "Username tidak boleh kosong"

            genderInput.equals("Pilih Jenis Kelamin") -> tampilToast("Jenis Kelamin harus dipilih")

            emailInput.isEmpty() -> edtEmail?.error = "Email tidak boleh kosong"

            telpInput.isEmpty() -> edtTelp?.error = "Telp tidak boleh kosong"

            alamatInput.isEmpty() -> edtAddress?.error = "Alamat tidak boleh kosong"

            ttlInput.isEmpty() -> edtTTL?.error = "Tanggal lahir tidak boleh kosong"
            else -> {
                val pengguna = User(
                    name = namaInput,username = usernameInput, kelamin =
                    genderInput, email = emailInput, telp = telpInput, alamat =
                    alamatInput, ttl = ttlInput
                )
                tambahDataUser(pengguna)
            }
        }
    }

    private fun tampilToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun tambahDataUser(pengguna: User): Job {
        return GlobalScope.launch {
            userDao?.tambahUser(pengguna)
        }
    }

    private fun initView() {
        spinnerGender = activity?.findViewById(R.id.spinnergender)

        edtName = activity?.findViewById(R.id.edtName)
        edtUsername = activity?.findViewById(R.id.edtUsername)
        edtEmail = activity?.findViewById(R.id.edtEmail)
        edtTelp = activity?.findViewById(R.id.edtTelp)
        edtAddress = activity?.findViewById(R.id.edtAddress)
        edtTTL = activity?.findViewById(R.id.edtTTL)

        btn_daftar = activity?.findViewById(R.id.btnDaftar)
        btn_daftar?.setOnClickListener { validasiInput()
        }

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        edtTTL?.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(view: View) {
                DatePickerDialog(
                    requireActivity(),
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateDateInView() {
        edtTTL?.text =
            SimpleDateFormat.getDateInstance(DateFormat.LONG, ULocale.forLocale(Locale.ENGLISH))
                .format(calendar.time)
    }


    override fun onDestroy() {
        super.onDestroy()
        db?.close()
        edtName = null
        edtUsername = null
        edtEmail = null
        edtTelp = null
        edtAddress = null
        btn_daftar = null
        spinnerGender = null
    }

    companion object {
        fun newInstance() = daftar()
    }
}