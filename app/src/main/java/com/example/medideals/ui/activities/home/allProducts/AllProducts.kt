package com.example.medideals.ui.activities.home.allProducts


import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.medideals.R
import com.example.medideals.data.model.delProduct.DeleteProduct
import com.example.medideals.data.model.showAllProduct.ShowAllProduct
import com.example.medideals.data.model.showAllProduct.ShowAllProductResult
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.ui.activities.home.dashboard.activeProducts.ActiveProductsViewModel
import com.example.medideals.ui.activities.home.dashboard.inActiveProducts.InActiveProductsViewModel
import com.example.medideals.utils.Constants.SEARCH_QUERY
import com.example.medideals.utils.Constants.TOTAL_ACTIVE_PRODUCTS
import com.example.medideals.utils.Constants.TOTAL_INACTIVE_PRODUCTS
import com.example.medideals.utils.Constants.TOTAL_LISTED_PRODUCTS
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_all_products.view.*


class AllProducts : Fragment() , HomeContract.ShowAllProductView, AllProducts_Adapter.DelProduct{



    var presenter: HomeContract.HomePresenter? = null
    var recyclerviewAllProducts: RecyclerView? = null
    var prodAdapter : AllProducts_Adapter? = null
    lateinit var allProductsViewModel: AllProductsViewModel
    lateinit var activeProductsViewModel: ActiveProductsViewModel
    lateinit var inActiveProductsViewModel: InActiveProductsViewModel
    var searchQuery : String = ""
    var title : String = ""

    companion object
    {
        var currentPage : Int = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragment_all_products, container, false)


        initialize(view)
        clickListener(view)




        return view
    }

    fun initialize(view : View)
    {
        currentPage = 1
        //initialize the view model
        allProductsViewModel = ViewModelProviders.of(this).get(AllProductsViewModel::class.java)
        activeProductsViewModel = ViewModelProviders.of(this).get(ActiveProductsViewModel::class.java)
        inActiveProductsViewModel = ViewModelProviders.of(this).get(InActiveProductsViewModel::class.java)


        recyclerviewAllProducts = view.findViewById(R.id.recyclerview_allProducts)
        recyclerviewAllProducts!!.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        prodAdapter = activity?.let { AllProducts_Adapter(it,this) }
        recyclerviewAllProducts!!.adapter = prodAdapter


        GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter = HomePresenterImp(this)


        val bundle = arguments
        if (bundle!=null && bundle.containsKey(TOTAL_LISTED_PRODUCTS))
        {
            title = TOTAL_LISTED_PRODUCTS
            GlobalHelper.setToolbar(getString(R.string.total_listed_products),homeBackIconVis= true,searchFilterHomeVis=true)
            observeLiveData(bundle.getString(SEARCH_QUERY))
        }
        else if (bundle!=null && bundle.containsKey(TOTAL_ACTIVE_PRODUCTS))
        {
            title = TOTAL_ACTIVE_PRODUCTS
            GlobalHelper.setToolbar(getString(R.string.total_active_products),homeBackIconVis= true,searchFilterHomeVis=true)
            observeLiveDataActiveProducts(bundle.getString(SEARCH_QUERY))
        }
        else if (bundle!=null && bundle.containsKey(TOTAL_INACTIVE_PRODUCTS))
        {
            title = TOTAL_INACTIVE_PRODUCTS
            GlobalHelper.setToolbar(getString(R.string.total_inactive_products),homeBackIconVis= true,searchFilterHomeVis=true)
            observeLiveDataInActiveProducts(bundle.getString(SEARCH_QUERY))
        }
        else
        {
            GlobalHelper.setToolbar(getString(R.string.all_prod),homeBackIconVis= true,searchFilterHomeVis=true)
            observeLiveData("")
        }

        HomeActivity.filterHome.visibility = View.GONE





        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }
    }


    fun clickListener(view : View)
    {
        HomeActivity.searchHome.setOnClickListener {
            HomeActivity.relLaySearchHome.visibility = View.VISIBLE
        }

        HomeActivity.relHomeSearchBack.setOnClickListener {

            HomeActivity.relLaySearchHome.visibility = View.GONE
            GlobalHelper.hideKeyBoard(activity as AppCompatActivity,view!!.rootShowAllProduct)
            HomeActivity.relHomeSearchEdt.setText("")
            searchQuery = ""

            val bundle = Bundle()
            bundle.putString(title,title)
            bundle.putString(SEARCH_QUERY,"")
            val frag = AllProducts()
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
                GlobalHelper.hideKeyBoard(activity as AppCompatActivity,view!!.rootShowAllProduct)
                searchQuery = txtview.text.toString().trim()

                val bundle = Bundle()
                bundle.putString(title,title)
                bundle.putString(SEARCH_QUERY,searchQuery)
                val frag = AllProducts()
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


    override fun onShowAllProductViewSuccess(showAllProduct: ShowAllProduct) {
        try {
            GlobalHelper.hideProgress()
            if (showAllProduct.status.equals("1")) {
                activity?.let {
                  //  recyclerviewAllProducts!!.adapter = AllProducts_Adapter(it,showAllProduct)
                }
            }
        } catch (ex: Exception) {
        }    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootShowAllProduct, it) }
        } catch (ex: Exception) {
        }    }


    private fun observeLiveData(search_query : String) {
        //observe live data emitted by view model
        allProductsViewModel.getPosts(search_query).observe(this, Observer { items ->
            GlobalHelper.hideProgress()
           // prodAdapter!!.submitList(null)
            prodAdapter?.let { it.submitList(items) }
        })

    }

    private fun observeLiveDataActiveProducts(search_query : String) {
        //observe live data emitted by view model
        activeProductsViewModel.getPosts(search_query).observe(this, Observer { items ->
            GlobalHelper.hideProgress()
            prodAdapter?.let { it.submitList(items) }
        })

    }

    private fun observeLiveDataInActiveProducts(search_query : String) {
        //observe live data emitted by view model
        inActiveProductsViewModel.getPosts(search_query).observe(this, Observer { items ->
            GlobalHelper.hideProgress()
            prodAdapter?.let { it.submitList(items) }
        })

    }



    override fun OnDelProductSuccess(prod_id: String) {
        AlertDialog.Builder(activity as AppCompatActivity)
            .setMessage("Are you sure want to Delete this Product?")
            .setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
                    GlobalHelper.showProgress(activity as AppCompatActivity)
                    presenter!!.deleteProduct(prod_id)
                })
            .setNegativeButton("No", null)
            .show()
    }

    override fun onDelProductViewSuccess(delProduct: DeleteProduct) {
        try {
            GlobalHelper.hideProgress()
            GlobalHelper.snackBar(view!!.rootShowAllProduct, delProduct.message)

            activity?.let { it.onBackPressed()
            GlobalHelper.replaceFragment(it,R.id.container_home,AllProducts()) }

        } catch (ex: Exception) {
        }
    }

    override fun onStop() {
        super.onStop()
        HomeActivity.filterHome.visibility = View.VISIBLE
    }


}
