package com.example.relativelayout_tubagusmuhammadrofialfaiz_xiirpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.relativelayout_tubagusmuhammadrofialfaiz_xiirpl.adapter.CategoriesAdapter
import com.example.relativelayout_tubagusmuhammadrofialfaiz_xiirpl.model.CategoriesModel
import com.example.relativelayout_tubagusmuhammadrofialfaiz_xiirpl.networking.Api
import org.json.JSONException
import org.json.JSONObject

class FoodActivity : AppCompatActivity() {

    lateinit var CategoriesAdapter:CategoriesAdapter
    lateinit var categoriesModel: CategoriesModel
    lateinit var categoryList: ArrayList<CategoriesModel>
    lateinit var rvCategories:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        categoryList=ArrayList()
        rvCategories=findViewById(R.id.rvFood)
        var layoutManager = GridLayoutManager(this, 2,
            RecyclerView.VERTICAL, false)
        rvCategories.layoutManager = layoutManager
        rvCategories.setHasFixedSize(true)
    }
    private val Categories  : Unit
        get(){
            AndroidNetworking.get(Api.Categories)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject?) {
                        try {
                            var foodArray = response?.getJSONArray("categories")
                            for (i in 0 until foodArray!!.length()){
                                var temp = foodArray.getJSONObject(i)
                                var dataApi = CategoriesModel()
                                dataApi.strCategory = temp.getString("strCategory")
                                dataApi.strCategoryThumb = temp.getString("strCategoryThump")
                                dataApi.strCategoryDescription = temp.getString("strCategoryDescription")


                                categoryList.add(dataApi)
                                showCategories()
                            }
                        }catch (e: JSONException){
                            e.printStackTrace()
                            Toast.makeText(this@FoodActivity, "Object Not Found",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText( this@FoodActivity,"close connection" ,
                            Toast.LENGTH_SHORT).show()
                    }

                })
        }

    private fun showCategories() {
        // inisialisasi caregorielAdapter dengan array category List dari API
        CategoriesAdapter = CategoriesAdapter(this, categoryList)
        rvCategories.adapter = CategoriesAdapter
    }
}