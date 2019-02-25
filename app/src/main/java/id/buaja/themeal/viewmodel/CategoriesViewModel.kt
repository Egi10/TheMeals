package id.buaja.themeal.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import id.buaja.themeal.model.CategoriesItem
import id.buaja.themeal.api.Response

class CategoriesViewModel : ViewModel() {
    private val list: MutableLiveData<List<CategoriesItem>>  = MutableLiveData()

    fun getCategories(): LiveData<List<CategoriesItem>>? {
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/categories.php")
            .setTag(this)
            .setPriority(Priority.LOW)
            .build()
            .getAsObject(Response::class.java, object : ParsedRequestListener<Response> {
                override fun onResponse(response: Response?) {
                    response?.let {
                        Log.d("Categories", "Sukses")
                        list.value = it.categories
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.d("Categories", anError?.message.toString())
                }
            })

        return list
    }
}