package com.example.medideals.ui.activities.home.addProduct


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity

import com.example.medideals.R
import com.example.medideals.data.model.addProduct.AddProduct
import com.example.medideals.data.saveData.AddProductModel
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.ImagepickerFragment
import com.example.medideals.utils.Validator
import kotlinx.android.synthetic.main.fragment_add_product.*
import kotlinx.android.synthetic.main.fragment_add_product.view.*
import kotlinx.android.synthetic.main.fragment_add_product.view.rootAddProduct
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.medideals.data.model.updateProduct.UpdateProduct
import com.example.medideals.data.saveData.SaveProductInf
import com.example.medideals.ui.activities.home.allProducts.AllProducts
import com.example.medideals.utils.Constants.PRODUCT_INFO
import com.example.medideals.utils.SharedPrefUtil


class AddProduct : ImagepickerFragment(), HomeContract.AddProductView {



    var presenter: HomeContract.HomePresenter? = null
    var addProdModel: AddProductModel = AddProductModel()
    var prodId : String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)


        initialize(view)
        clickListener(view)





        return view
    }

    fun initialize(view: View) {
        presenter = HomePresenterImp(this)


        val bundle = arguments
        if (bundle!=null && bundle.containsKey(PRODUCT_INFO))
        {
            GlobalHelper.setToolbar(getString(R.string.edit_product), homeBackIconVis = true)
            view.productClick_AddProd.text = getString(R.string.update_product)

            val prodInfo = bundle.getParcelable(PRODUCT_INFO) as SaveProductInf

            view.prodName_addProduct.setText(prodInfo.product_name)
            view.prodDesc_addProduct.setText(prodInfo.product_description)
            view.txtChooseFile.text = prodInfo.image
            view.retailPrice_addProduct.setText(prodInfo.maximum_retail_Price)
            view.disPrice_addProduct.setText(prodInfo.discounted_price)
            view.invQty_addProduct.setText(prodInfo.quantity)

            try {
                var dis = prodInfo.discounted_price.toDouble()
                var amt =  (prodInfo.maximum_retail_Price.toDouble()*(100-dis))/100
                if (amt>=0)
                    view.totalDis_addProduct.setText(amt.toString())
            }catch (ex : Exception){
            }

            prodId = prodInfo.product_id

            addProdModel.prodPic = prodInfo.image


        }
        else
            GlobalHelper.setToolbar(getString(R.string.add_roduct), homeBackIconVis = true)


        val spinnerCat = view.findViewById(R.id.spinner_cat) as Spinner
        val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, activity!!.resources.getStringArray(R.array.prod_categories))
        spinnerCat.adapter = arrayAdapter


        spinnerCat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                addProdModel.prodCat = spinnerCat.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }


    }

    fun clickListener(view: View) {
        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }

        view.txtChooseFile.setOnClickListener {
            getImage(activity, 0)
        }

        view.disPrice_addProduct.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                   var dis = view.disPrice_addProduct.text.toString().trim().toDouble()
                   var amt =  (view.retailPrice_addProduct.text.toString().trim().toDouble()*(100-dis))/100
                    if (amt>=0)
                    view.totalDis_addProduct.setText(amt.toString())

                    if (dis>100)
                        Toast.makeText(activity,"Discount Percent can not be greater than 100",Toast.LENGTH_SHORT).show()
                }catch (ex : Exception){
                }
            }

        })

        view.retailPrice_addProduct.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    var dis = view.disPrice_addProduct.text.toString().trim().toDouble()
                    var amt =  (view.retailPrice_addProduct.text.toString().trim().toDouble()*(100-dis))/100
                    if (amt>=0)
                        view.totalDis_addProduct.setText(amt.toString())
                }catch (ex : Exception){
                }
            }

        })


        view.uploadProduct_AddProd.setOnClickListener {

            addProdModel.prodName = prodName_addProduct.text.toString().trim()
            addProdModel.prodDesc = prodDesc_addProduct.text.toString().trim()
            addProdModel.prodRetailPrc = retailPrice_addProduct.text.toString().trim()
            addProdModel.prodDisPrc = disPrice_addProduct.text.toString().trim()
            addProdModel.prodTotalDis = totalDis_addProduct.text.toString().trim()
            addProdModel.prodInvQty = invQty_addProduct.text.toString().trim()
            addProdModel.prodOrderQty = orderQty_addProduct.text.toString().trim()
            addProdModel.prodCompName = compName_addProduct.text.toString().trim()


            if (Validator.getsInstance().addProductValidator(addProdModel, rootAddProduct, activity as AppCompatActivity))
            {
                GlobalHelper.showProgress(activity as AppCompatActivity)

                if (productClick_AddProd.text.toString().trim().equals(getString(R.string.upload_product)))
                    presenter!!.addProduct(addProdModel)
                else
                {
                    val prodInfo = SaveProductInf()

                    prodInfo.product_name = addProdModel.prodName
                    prodInfo.product_description = addProdModel.prodDesc
                    prodInfo.maximum_retail_Price = addProdModel.prodRetailPrc
                    prodInfo.discounted_price = addProdModel.prodDisPrc
                    prodInfo.discount_percent = addProdModel.prodTotalDis
                    prodInfo.image = addProdModel.prodPic
                    prodInfo.product_id = prodId
                    prodInfo.company_name = compName_addProduct.text.toString().trim()
                    prodInfo.vendor_id = SharedPrefUtil.getInstance()!!.getUserId().toString()
                    prodInfo.category = addProdModel.prodCat
                    prodInfo.minquantity = addProdModel.prodOrderQty
                    prodInfo.quantity = addProdModel.prodInvQty

                    presenter!!.updateProduct(prodInfo)
                }
            }

        }
    }

    override fun onAddProductViewSuccess(addProduct: AddProduct) {
        try {
            GlobalHelper.hideProgress()
            addProduct.message.let { GlobalHelper.snackBar(view!!.rootAddProduct, it) }
            activity?.let { it.onBackPressed() }
        } catch (ex: Exception) {
        }
    }

    override fun onUpdateProductViewSuccess(updateProduct: UpdateProduct) {
        try {
            GlobalHelper.hideProgress()
            updateProduct.message.let { GlobalHelper.snackBar(view!!.rootAddProduct, it) }
            activity?.let { it.supportFragmentManager.popBackStack() }
            activity?.let { it.supportFragmentManager.popBackStack() }
            GlobalHelper.replaceFragment(activity as AppCompatActivity,R.id.container_home,AllProducts())
        } catch (ex: Exception) {
        }    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootAddProduct, it) }
        } catch (ex: Exception) {
        }
    }

    override fun selectedImage(imagePath: String?) {
         addProdModel.prodPic = imagePath.toString()
         view!!.txtChooseFile.text = imagePath.toString()
    }


}
