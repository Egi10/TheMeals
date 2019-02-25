package id.buaja.themeal.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.androidnetworking.AndroidNetworking
import id.buaja.themeal.R
import id.buaja.themeal.adapter.AdapterCategories
import id.buaja.themeal.model.CategoriesItem
import id.buaja.themeal.viewmodel.CategoriesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var adapterCategories: AdapterCategories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(this)

        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)

        swipeRefresh.post {
            loadData()
        }

        swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        categoriesViewModel.getCategories()?.observe(this, Observer<List<CategoriesItem>> {
            it?.let { list ->
                adapterCategories = AdapterCategories(this, list) {

                }
                recyclerView.layoutManager = GridLayoutManager(this, 2)
                recyclerView.adapter = adapterCategories
            }
        })
    }
}
