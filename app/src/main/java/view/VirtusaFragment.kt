package view


import adapter.CountryRecyclerAdapter
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.virtusa.com.poc_virtusa.R
import kotlinx.android.synthetic.main.fragment_virtusa.*
import model.CountryData
import model.ResponseData
import viewmodel.SampleViewModel
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class VirtusaFragment : Fragment() {

    var countryRecyclerAdapter: CountryRecyclerAdapter? = null
    var filterCountryList: ArrayList<CountryData>? = null
    var swipeRefreshCountryList: ArrayList<CountryData>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_virtusa, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeFreshLayoutViewModel.setOnRefreshListener {
            countryRecyclerAdapter!!.clear()
            countryRecyclerAdapter!!.addAll(this.swipeRefreshCountryList!!)
            swipeFreshLayoutViewModel.isRefreshing = false
        }

        val sampleViewModel = ViewModelProviders.of(this).get(SampleViewModel::class.java)
        sampleViewModel.getResponseDataList().observe(this, object : Observer<ResponseData> {
            override fun onChanged(t: ResponseData?) {

                filterCountryList = ArrayList<CountryData>()
                swipeRefreshCountryList = ArrayList()

                val countryList = t!!.rows

                for (i in 0 until countryList.size) {
                    if (countryList.get(i).title != null || countryList.get(i).description != null) {
                        filterCountryList!!.add(countryList[i])
                        swipeRefreshCountryList!!.add(countryList[i])

                    }
                }

                Collections.sort(filterCountryList, Sortbytitle())
                countryRecyclerAdapter = CountryRecyclerAdapter(this@VirtusaFragment.context, filterCountryList!!)
                val llm = LinearLayoutManager(this@VirtusaFragment.context)
                llm.orientation = LinearLayoutManager.VERTICAL
                virtusaRecyclerView.layoutManager = llm
                virtusaRecyclerView.adapter = countryRecyclerAdapter
            }
        })
    }

    internal inner class Sortbytitle : Comparator<CountryData> {
        override fun compare(p0: CountryData?, p1: CountryData?): Int {
            return p0!!.title!!.compareTo(p1!!.title!!)
        }
    }

}


