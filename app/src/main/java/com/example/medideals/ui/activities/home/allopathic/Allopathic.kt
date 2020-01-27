package com.example.medideals.ui.activities.home.allopathic


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.medideals.R
import com.example.medideals.data.model.addCart.AddCart
import com.example.medideals.data.model.allopathicModel.AllopathicModel
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.utils.Constants.ALLOPATHIC_TYPE
import com.example.medideals.utils.Constants.CATEGORY_ID
import com.example.medideals.utils.Constants.TITLE_CATEGORY
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_allopathic.*
import kotlinx.android.synthetic.main.fragment_allopathic.view.*
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.example.medideals.data.model.getBrands.GetBrands
import com.example.medideals.data.model.getCities.GetCities
import com.example.medideals.data.model.getStates.GetStates
import com.example.medideals.data.saveData.SaveAllopathicData
import com.example.medideals.utils.Constants.ALLOPATHIC_DATA
import com.example.medideals.utils.Constants.SEARCH_QUERY
import com.example.medideals.utils.SharedPrefUtil


class Allopathic : Fragment(), HomeContract.AllopathicView, Allopathic_Adapter.AllopathicInterface {



    var presenter: HomeContract.HomePresenter? = null
    var recyclerview_allopathic: RecyclerView? = null
    var allopathicViewModel: AllopathicViewModel? = null
    var allopathic_Adapter: Allopathic_Adapter? = null
    var catId: String = ""
    var title: String = ""
    var pageList : PagedList<com.example.medideals.data.model.allopathicModel.Result>? = null
    var searchQuery : String = ""
    var arrayListStateId : ArrayList<String> = ArrayList()

    var spinnerGetBrands : Spinner? = null
    var spinnerGetStates : Spinner? = null
    var spinnerGetCities : Spinner? = null

    var saveAllopathicData : SaveAllopathicData = SaveAllopathicData()

    companion object {
        var currentPageAllopathic: Int = 1
        var noDataAllopathic: ImageView? = null
        /*fun updatEmptyList()
        {
            activity!!.runOnUiThread(
                object : Runnable {
                    override fun run() {
                        noDataAllopathic!!.visibility = View. VISIBLE
                    }
                }
            )

            runOthreadThread()

        }*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_allopathic, container, false)



        initialize(view)
        clickListener(view)

        return view
    }


    fun initialize(view: View) {

        currentPageAllopathic = 1


        allopathicViewModel = ViewModelProviders.of(this).get(AllopathicViewModel::class.java)
        noDataAllopathic = view.findViewById(R.id.noData_allopathic) as ImageView
        recyclerview_allopathic = view.findViewById(R.id.recyclerview_allopathic) as RecyclerView
        recyclerview_allopathic!!.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        allopathic_Adapter = activity?.let { Allopathic_Adapter(it, this) }
        recyclerview_allopathic!!.adapter = allopathic_Adapter

        spinnerGetBrands = view.findViewById<Spinner>(R.id.spinnerGetBrands)
        spinnerGetStates = view.findViewById<Spinner>(R.id.spinnerGetStates)
        spinnerGetCities= view.findViewById<Spinner>(R.id.spinnerGetCities)





        val bundle = arguments

        if (bundle != null && bundle.containsKey(TITLE_CATEGORY) && bundle.containsKey(CATEGORY_ID) && bundle.containsKey(ALLOPATHIC_DATA)) {
            val title_ = bundle.getString(TITLE_CATEGORY)
            val cat_id = bundle.getString(CATEGORY_ID)
            searchQuery = bundle.getString(SEARCH_QUERY)
            saveAllopathicData = bundle.getParcelable(ALLOPATHIC_DATA)
            catId = cat_id
            title = title_


            GlobalHelper.setToolbar(title, homeMenuIconVis = true, searchFilterHomeVis = true, relFilterHomeVis = true)

            GlobalHelper.showProgress(activity as AppCompatActivity)
            presenter = HomePresenterImp(this)
            presenter!!.getBrands()
            presenter!!.getStates()
            // presenter?.allopathic(cat_id)

            observeLiveData(cat_id,searchQuery,saveAllopathicData.minPrice,saveAllopathicData.maxPrice,searchQuery,saveAllopathicData.discount,saveAllopathicData.brand,saveAllopathicData.state,saveAllopathicData.city)

        }

         else if (bundle != null && bundle.containsKey(TITLE_CATEGORY) && bundle.containsKey(CATEGORY_ID)) {
            val title_ = bundle.getString(TITLE_CATEGORY)
            val cat_id = bundle.getString(CATEGORY_ID)
            searchQuery = bundle.getString(SEARCH_QUERY)
            catId = cat_id
            title = title_


            GlobalHelper.setToolbar(title, homeMenuIconVis = true, searchFilterHomeVis = true, relFilterHomeVis = true)

            GlobalHelper.showProgress(activity as AppCompatActivity)
            presenter = HomePresenterImp(this)
            presenter!!.getBrands()
            presenter!!.getStates()
            // presenter?.allopathic(cat_id)

            observeLiveData(cat_id,searchQuery,"10","500000",searchQuery,"","","","")

        }


        val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Write code to perform some action when progress is changed.
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.
               // seekBar.progress
                try {
                    if (seekBar.progress<10)
                      saveAllopathicData.maxPrice = "10"
                    else
                      saveAllopathicData.maxPrice = seekBar.progress.toString()

                }catch (ex : Exception){}


            }
        })



    }

    fun clickListener(view : View)
    {

       /* view.rel_brandSpinner.setOnClickListener {
            spinnerGetBrands!!.performClick()
        }*/

        HomeActivity.searchHome.setOnClickListener {
            HomeActivity.relLaySearchHome.visibility = View.VISIBLE
        }

       // rel_filter_allopathic
        HomeActivity.relFilterTickHome.setOnClickListener {

            saveAllopathicData.device_id = SharedPrefUtil!!.getInstance()?.getDeviceToken().toString()
            saveAllopathicData.cat_id = catId

            if (view.radioBtn1.isChecked)
                saveAllopathicData.discount = "1-10"
            else if (view.radioBtn2.isChecked)
                saveAllopathicData.discount = "11-20"
            else if (view.radioBtn3.isChecked)
                saveAllopathicData.discount = "21-30"
            else if (view.radioBtn4.isChecked)
                saveAllopathicData.discount = "31-40"
            else if (view.radioBtn5.isChecked)
                saveAllopathicData.discount = "41-50"
            else if (view.radioBtn6.isChecked)
                saveAllopathicData.discount = "51-60"
            else if (view.radioBtn7.isChecked)
                saveAllopathicData.discount = "61-70"
            else if (view.radioBtn8.isChecked)
                saveAllopathicData.discount = "71-80"
            else if (view.radioBtn9.isChecked)
                saveAllopathicData.discount = "81-90"

            if (spinnerGetBrands!!.selectedItem.equals("Select Brand"))
                GlobalHelper.snackBar(view!!.rootAllopathic,"Please Select Brand")
            else if (spinnerGetStates!!.selectedItem.equals("Select State"))
                GlobalHelper.snackBar(view!!.rootAllopathic,"Please Select Select")
            else if (spinnerGetCities!!.selectedItem.equals("Select City"))
                GlobalHelper.snackBar(view!!.rootAllopathic,"Please Select City")
            else if (view.search_filter.text.toString().toString().isEmpty())
                GlobalHelper.snackBar(view!!.rootAllopathic,"Please Enter Product Name")
            else
            {

                val bundle = Bundle()
                bundle.putString(TITLE_CATEGORY,title)
                bundle.putString(CATEGORY_ID,catId)
                bundle.putString(SEARCH_QUERY,searchQuery)
                bundle.putParcelable(ALLOPATHIC_DATA,saveAllopathicData)
                val frag = Allopathic()
                frag.arguments = bundle

                activity!!.supportFragmentManager.popBackStack()

                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.container_home,
                    frag
                ).addToBackStack(null).commit()
            }



        }

        HomeActivity.relFilterCloseHome.setOnClickListener {
            GlobalHelper.setToolbar(title, homeMenuIconVis = true, searchFilterHomeVis = true, relFilterHomeVis = true)
            view.rel_filter_allopathic.visibility = View.GONE
        }

        HomeActivity.filterHome.setOnClickListener {
            GlobalHelper.setToolbar(title, relFilterCloseHomeVis = true, relFilterTickHomeVis = true)
            view.rel_filter_allopathic.visibility = View.VISIBLE
        }

        HomeActivity.relHomeSearchBack.setOnClickListener {

            HomeActivity.relLaySearchHome.visibility = View.GONE
            GlobalHelper.hideKeyBoard(activity as AppCompatActivity,rootAllopathic)
            HomeActivity.relHomeSearchEdt.setText("")
            searchQuery = ""


            //  edit order api (what to pass), payment api,  filter, add/view location(yet to discuss), razor api_to add



            val bundle = Bundle()
            bundle.putString(TITLE_CATEGORY,title)
            bundle.putString(CATEGORY_ID,catId)
            bundle.putString(SEARCH_QUERY,searchQuery)
            val frag = Allopathic()
            frag.arguments = bundle

            activity!!.supportFragmentManager.popBackStack()

            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.container_home,
                frag
            ).addToBackStack(null).commit()

        }

        HomeActivity.relHomeSearchClose.setOnClickListener {
            HomeActivity.relHomeSearchEdt.setText("")
        }



        HomeActivity.relHomeSearchEdt.setOnEditorActionListener { txtview, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {


              //  HomeActivity.relLaySearchHome.visibility = View.GONE
                GlobalHelper.hideKeyBoard(activity as AppCompatActivity,rootAllopathic)
                searchQuery = txtview.text.toString().trim()


                val bundle = Bundle()
                bundle.putString(TITLE_CATEGORY,title)
                bundle.putString(CATEGORY_ID,catId)
                bundle.putString(SEARCH_QUERY,searchQuery)
                val frag = Allopathic()
                frag.arguments = bundle

                activity!!.supportFragmentManager.popBackStack()

                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.container_home,
                    frag
                ).addToBackStack(null).commit()

            }
            true
        }

    }

    override fun onAllopathicViewSuccess(allopathicModel: AllopathicModel) {
        try {
            GlobalHelper.hideProgress()
            /*activity?.let {
                recyclerview_allopathic!!.adapter = Allopathic_Adapter(it)
            }*/
        } catch (ex: Exception) {
        }
    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            //   noData_allopathic.visibility = View. VISIBLE
        } catch (ex: Exception) {
        }
    }

    private fun observeLiveData(cat_id: String,search_query : String, minPrice : String, maxPrice : String, search : String, dis : String,
                                brand : String, state : String, city : String) {
        //observe live data emitted by view model

      //  pageList = ArrayList<com.example.medideals.data.model.allopathicModel.Result>
        /*allopathicViewModel!!.getPosts(cat_id).observe(this, Observer { items ->
            GlobalHelper.hideProgress()
            allopathic_Adapter?.let { it.submitList(items) }
        })*/


        allopathicViewModel!!.getPosts(cat_id,search_query,minPrice,maxPrice,search,dis,brand,state,city).observe(this,Observer<PagedList<com.example.medideals.data.model.allopathicModel.Result>>(){
        items->
            pageList = items
            GlobalHelper.hideProgress()
            allopathic_Adapter?.let { it.submitList(items) }

        })

    }



    override fun OnAllopathicSuccess(product_id: String, qty : String) {
        presenter!!.addCart(product_id, qty, ALLOPATHIC_TYPE)
    }

    override fun onAddCartViewSuccess(addCart: AddCart) {
        try {
            GlobalHelper.snackBar(view!!.rootAllopathic, addCart.message)
            if (addCart.status.equals("1")) {

                searchQuery = ""
                val bundle = Bundle()
                bundle.putString(TITLE_CATEGORY, title)
                bundle.putString(CATEGORY_ID, catId)
                bundle.putString(SEARCH_QUERY,searchQuery)
                val frag = Allopathic()
                frag.arguments = bundle

                activity!!.supportFragmentManager.popBackStack()

                activity!!.supportFragmentManager.beginTransaction().replace(R.id.container_home, frag)
                    .addToBackStack(null).commit()
              }
            } catch (ex: Exception) {
            }
        }

    override fun onGetBrandsViewSuccess(getBrands: GetBrands) {
      try {
           if (getBrands.status.equals("1"))
               getBrandsSpinner(getBrands)
      }catch (ex : Exception){}
    }

    override fun onGetStatesViewSuccess(getStates: GetStates) {
        try {
            if (getStates.status.equals("1"))
                getStatesSpinner(getStates)
        }catch (ex : Exception){}
    }

    override fun onGetCitiesViewSuccess(getCities: GetCities) {
        try {
            if (getCities.status.equals("1"))
                getCitiesSpinner(getCities)
        }catch (ex : Exception){}
    }

    fun getBrandsSpinner(brands: GetBrands)
    {
        var arrayListBrands : ArrayList<String> = ArrayList()
        arrayListBrands.add("Select Brand")
        for (i in 0..brands.record.size-1)
        {
            arrayListBrands.add(brands.record.get(i).brand_name)
        }

        if (spinnerGetBrands != null) {
            val arrayAdapter = ArrayAdapter(activity as AppCompatActivity, android.R.layout.simple_spinner_item, arrayListBrands)
            spinnerGetBrands!!.adapter = arrayAdapter

            spinnerGetBrands!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                   // Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + personNames[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

    }

    fun getStatesSpinner(brands: GetStates)
    {
        var arrayListStates : ArrayList<String> = ArrayList()
        arrayListStates.add("Select State")
        arrayListStateId.clear()
        arrayListStateId.add("0")
        for (i in 0..brands.record.size-1)
        {
            arrayListStates.add(brands.record.get(i).state_name)
            arrayListStateId.add(brands.record.get(i).state_id)
        }

        if (spinnerGetStates != null) {
            val arrayAdapter = ArrayAdapter(activity as AppCompatActivity, android.R.layout.simple_spinner_item, arrayListStates)
            spinnerGetStates!!.adapter = arrayAdapter

            spinnerGetStates!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    // Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + personNames[position], Toast.LENGTH_SHORT).show()
                   if (parent.getItemAtPosition(position).equals("Select State"))
                       getCitiesEmptySpinner()
                   else
                       presenter!!.getCities(arrayListStateId.get(position))

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

    }

    fun getCitiesSpinner(brands: GetCities)
    {
        var arrayListCities : ArrayList<String> = ArrayList()
        arrayListCities.add("Select City")
        for (i in 0..brands.record.size-1)
        {
            arrayListCities.add(brands.record.get(i).city_name)
        }

        if (spinnerGetCities != null) {
            val arrayAdapter = ArrayAdapter(activity as AppCompatActivity, android.R.layout.simple_spinner_item, arrayListCities)
            spinnerGetCities!!.adapter = arrayAdapter

            spinnerGetCities!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    // Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + personNames[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }
    }

    fun getCitiesEmptySpinner()
    {
        var arrayListCities : ArrayList<String> = ArrayList()
        arrayListCities.add("Select City")


        if (spinnerGetCities != null) {
            val arrayAdapter = ArrayAdapter(activity as AppCompatActivity, android.R.layout.simple_spinner_item, arrayListCities)
            spinnerGetCities!!.adapter = arrayAdapter

            spinnerGetCities!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    // Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + personNames[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

    }





/*    fun runOthreadThread()
    {

        try {

            activity!!.runOnUiThread(
                object : Runnable {
                    override fun run() {
                        noDataAllopathic!!.visibility = View. VISIBLE
                    }
                }
            )

        }catch (ex : Exception){}
    }*/


    }
