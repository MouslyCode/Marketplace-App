package com.example.tumbasmas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class WelcomeFragment : Fragment() {

    private var listUser: RecyclerView? = null
    private lateinit var listPengguna: List<User>
    private var db: AppDatabase? = null
    private var UserDAO: UserDao? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        ambilDataUser()

    }

    private fun initLocalDB() {
        db = AppDatabase.getAppDatabase(requireActivity())
        UserDAO = db?.userDao()
    }


    private fun tampilPengguna() {
        listUser= activity?.findViewById(R.id.listUser)
        listUser?.layoutManager = LinearLayoutManager(activity)
        listUser?.adapter = UserAdapter(
            requireActivity(),
            listPengguna as ArrayList<User>
        )
    }

    private fun ambilDataUser() {
        listPengguna = ArrayList()
        UserDAO?.ambilSemuaUser()?.observe(requireActivity()) { r ->
            listPengguna = r.toMutableList()
            when {
                listPengguna.isEmpty() -> {
                    Toast.makeText(requireActivity(), "Belum Ada Data", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    tampilPengguna()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listUser = null

    }

    companion object {
        fun newInstance(): WelcomeFragment {
            return WelcomeFragment()
        }
    }
}