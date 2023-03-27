package com.example.newzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newzz.Adapter.NewsAdapter
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rv:RecyclerView
    var pagenum=1

    var totalresult=-1;
    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         rv=findViewById(R.id.rv)

        //initialize rv with adapter binded by  empty mutlable list articles
        adapter = NewsAdapter(this@MainActivity,articles)


        rv.adapter=adapter

        rv.layoutManager=LinearLayoutManager(this@MainActivity)
        Log.d("1","no error  $totalresult")
        getnews()

            pagenum++
            getnews()



    }

     fun getnews() {
        val news = NewsService.newsInstance.getheadline("in", pagenum)
        //all request we make are put in queue
        // and queue one by one processes it
        news.enqueue(object : retrofit2.Callback<News>
        // as soon as 1 request is processed it calls its callback
        {
            // callbacks 'on response' states request was successful
            override fun onResponse(call: Call<News>, response: Response<News>) {
                //on success we will get News in response
                // News is coming coz in newsInterface we are calling News(Call<News>)
                val news = response.body()
                if (news != null) {
//                    Log.d("Aditya1", news.toString())
                    // when successful , we are adding news(response body's one).articles
                    // to empty mutable list and calling adapter that a change is done
                    articles.addAll(news.articles)
                     totalresult=news.totalResults
                    Log.d("3","no error  $totalresult")
                    adapter.notifyDataSetChanged()


                }
            }

            // callbacks 'on failure' states request was unsuccessful with t as reason
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("aditya", "Error n fetxhing", t)
            }
        })

    }
}