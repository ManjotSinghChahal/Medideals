package com.example.medideals.ui.activities.home

import com.example.medideals.R
import com.example.medideals.data.model.addCard.AddCard
import com.example.medideals.data.model.addCart.AddCart
import com.example.medideals.data.model.addEnquire.AddEnquire
import com.example.medideals.data.model.addProduct.AddProduct
import com.example.medideals.data.model.allopathicModel.AllopathicModel
import com.example.medideals.data.model.bankDeatilsModel.BankDeatilsModel
import com.example.medideals.data.model.buySubscription.BuySubscription
import com.example.medideals.data.model.changeOrderStatus.ChangeOrderStatus
import com.example.medideals.data.model.contactUs.Contactus
import com.example.medideals.data.model.delProduct.DeleteProduct
import com.example.medideals.data.model.delProductCart.DelProductCart
import com.example.medideals.data.model.editCartModel.EditCartModel
import com.example.medideals.data.model.editDocNumber.EditDocNumber
import com.example.medideals.data.model.getBrands.GetBrands
import com.example.medideals.data.model.getCart.GetCart
import com.example.medideals.data.model.getCities.GetCities
import com.example.medideals.data.model.getProfile.GetProfile
import com.example.medideals.data.model.getStates.GetStates
import com.example.medideals.data.model.getSubscription.GetSubscription
import com.example.medideals.data.model.homeListings.HomeListings
import com.example.medideals.data.model.logout.Logout
import com.example.medideals.data.model.orderPlaced.OrderPlacedModel
import com.example.medideals.data.model.paymentDetailsModel.PaymentDetailsModel
import com.example.medideals.data.model.productDetail.ProductDetailModel
import com.example.medideals.data.model.responses.Responses
import com.example.medideals.data.model.showAllProduct.ShowAllProduct
import com.example.medideals.data.model.totalCount.TotalCount
import com.example.medideals.data.model.updateDeviceId.UpdateDeviceId
import com.example.medideals.data.model.updateLatLng.UpdateLatLng
import com.example.medideals.data.model.updateProduct.UpdateProduct
import com.example.medideals.data.model.updateProfile.UpdateProfile
import com.example.medideals.data.saveData.*
import com.example.medideals.utils.Constants.ALLOPATHIC_TYPE
import com.example.medideals.utils.Constants.HOME
import com.example.medideals.utils.Constants.HOME_ACTIVITY
import com.example.medideals.utils.Constants.ORDERS_PLACED
import com.example.medideals.utils.Constants.ORDER_RECEIVED
import com.example.medideals.utils.Constants.PRODUCT_DETAIL
import com.example.medideals.utils.Constants.PROFILE_TYPE
import com.example.medideals.utils.Constants.TOTAL_ORDERS_PLACED
import com.example.passerby.ui.baseClasses.App

class HomePresenterImp() : HomeContract.HomePresenter, HomeContract.OnHomeCompleteListener {



    lateinit var interactor: HomeContract.HomeInteractor
    var homeListingsView: HomeContract.HomeListingsView? = null
    var productDetailView: HomeContract.ProductDetailView? = null
    var getCartView: HomeContract.GetCartView? = null
    var contactUsView: HomeContract.ContactUsView? = null
    var addEnquireView: HomeContract.AddEnquireView? = null
    var orderPlacedView: HomeContract.OrderPlacedView? = null
    var addProductView: HomeContract.AddProductView? = null
    var homeActivityView: HomeContract.HomeActivityView? = null
    var showAllProductView: HomeContract.ShowAllProductView? = null
    var orderReceivedView: HomeContract.OrderReceivedView? = null
    var bankDetailsView: HomeContract.BankDeatilsView? = null
    var subscriptionView: HomeContract.SubscriptionView? = null
    var myAccountView: HomeContract.MyAccountView? = null
    var responsesView: HomeContract.ResponsesView? = null
    var allopathicView: HomeContract.AllopathicView? = null
    var checkoutView: HomeContract.CheckoutView? = null
    var profileView: HomeContract.ProfileView? = null



    constructor(homeListings_view: HomeContract.HomeListingsView) : this() {
        interactor = HomeInteractorImp()
        homeListingsView = homeListings_view
    }

    constructor(homeActivity_view: HomeContract.HomeActivityView) : this() {
        interactor = HomeInteractorImp()
        homeActivityView = homeActivity_view
    }


    constructor(productDetail_view: HomeContract.ProductDetailView) : this() {
        interactor = HomeInteractorImp()
        productDetailView = productDetail_view
    }

    constructor(getCart_view: HomeContract.GetCartView) : this() {
        interactor = HomeInteractorImp()
        getCartView = getCart_view
    }

    constructor(contactUs_view: HomeContract.ContactUsView) : this() {
        interactor = HomeInteractorImp()
        contactUsView = contactUs_view
    }

    constructor(addEnquire_view: HomeContract.AddEnquireView) : this() {
        interactor = HomeInteractorImp()
        addEnquireView = addEnquire_view
    }

    constructor(orderPlaced_view: HomeContract.OrderPlacedView) : this() {
        interactor = HomeInteractorImp()
        orderPlacedView = orderPlaced_view
    }

    constructor(addProduct_view: HomeContract.AddProductView) : this() {
        interactor = HomeInteractorImp()
        addProductView = addProduct_view
    }

    constructor(showAllProduct_view: HomeContract.ShowAllProductView) : this() {
        interactor = HomeInteractorImp()
        showAllProductView = showAllProduct_view
    }

    constructor(orderReceived_view: HomeContract.OrderReceivedView) : this() {
        interactor = HomeInteractorImp()
        orderReceivedView = orderReceived_view
    }

    constructor(bankDetails_view: HomeContract.BankDeatilsView) : this() {
        interactor = HomeInteractorImp()
        bankDetailsView = bankDetails_view
    }

    constructor(subscription_view: HomeContract.SubscriptionView) : this() {
        interactor = HomeInteractorImp()
        subscriptionView = subscription_view
    }

    constructor(myAccount_view: HomeContract.MyAccountView) : this() {
        interactor = HomeInteractorImp()
        myAccountView = myAccount_view
    }

    constructor(checkout_view: HomeContract.CheckoutView) : this() {
        interactor = HomeInteractorImp()
        checkoutView = checkout_view
    }

    constructor(responses_view: HomeContract.ResponsesView) : this() {
        interactor = HomeInteractorImp()
        responsesView = responses_view
    }

    constructor(allopathic_view: HomeContract.AllopathicView) : this() {
        interactor = HomeInteractorImp()
        allopathicView = allopathic_view
    }

    constructor(profile_view: HomeContract.ProfileView) : this() {
        interactor = HomeInteractorImp()
        profileView = profile_view
    }




    override fun homeListing() {
        if (App.hasNetwork()) {
            interactor.homeListing(this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }
    }

    override fun productDetail(product_id: String) {
        if (App.hasNetwork()) {
            interactor.productDetail(product_id, this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun addCart(product_id: String, quantity: String, screen: String) {
        if (App.hasNetwork()) {
            interactor.addCart(product_id, quantity,screen,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }
    }

    override fun getCart(screen : String) {
        if (App.hasNetwork()) {
            interactor.getCart(screen,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun delProductCartDetail(product_id: String) {
        if (App.hasNetwork()) {
            interactor.delProductCartDetail(product_id,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun contactUs(name: String, email: String, phone: String, title: String, message: String) {
        if (App.hasNetwork()) {
            interactor.contactUs(name,email,phone,title,message,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun addEnquire(vendor_id: String, vendor_email: String, message: String) {
        if (App.hasNetwork()) {
            interactor.addEnquire(vendor_id,vendor_email,message,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }

    override fun orderPlaced(vendor_id: String, page: String) {
        if (App.hasNetwork()) {
            interactor.orderPlaced(vendor_id,page,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun addProduct(addPlacedModel: AddProductModel) {
        if (App.hasNetwork()) {
            interactor.addProduct(addPlacedModel,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun updateDeviceID() {
        if (App.hasNetwork()) {
            interactor.updateDeviceID(this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }

    override fun updateLatLng(lat: String, lng: String) {
        if (App.hasNetwork()) {
            interactor.updateLatLng(lat,lng,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }

    override fun logout() {
        if (App.hasNetwork()) {
            interactor.logout(this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun showAllProduct(page: String) {
        if (App.hasNetwork()) {
            interactor.showAllProduct(page,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }

    override fun deleteProduct(prod_id: String) {
        if (App.hasNetwork()) {
            interactor.deleteProduct(prod_id,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun changeOrderStatus(screen : String, list_id: String, status: String) {
        if (App.hasNetwork()) {
            interactor.changeOrderStatus(screen, list_id,status,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun editDocNum(list_id: String, docNum: String) {
        if (App.hasNetwork()) {
            interactor.editDocNum(list_id,docNum,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun updateProduct(saveProductInf: SaveProductInf) {
        if (App.hasNetwork()) {
            interactor.updateProduct(saveProductInf,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun bankDetails() {
        if (App.hasNetwork()) {
            interactor.bankDetails(this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }

    override fun addCardDetails(addCardInfo: AddCardInfo) {
        if (App.hasNetwork()) {
            interactor.addCardDetails(addCardInfo,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }

    override fun getSubscription() {
        if (App.hasNetwork()) {
            interactor.getSubscription(this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun buySubscription(subscription_id: String) {
        if (App.hasNetwork()) {
            interactor.buySubscription(subscription_id,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun totalCount() {
        if (App.hasNetwork()) {
            interactor.totalCount(this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }      }
    override fun responses() {
        if (App.hasNetwork()) {
            interactor.responses(this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun allopathic(cat_id: String) {
        if (App.hasNetwork()) {
            interactor.allopathic(cat_id,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun editCart(record: String) {
        if (App.hasNetwork()) {
            interactor.editCart(record,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun getProfile(screen: String) {
        if (App.hasNetwork()) {
            interactor.getProfile(screen,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }

    override fun payment(paymentDetails: PaymentDetails) {
        if (App.hasNetwork()) {
            interactor.payment(paymentDetails,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }     }

    override fun updateProfile(saveProfileData: SaveProfileData) {
        if (App.hasNetwork()) {
            interactor.updateProfile(saveProfileData,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }
    }


    override fun getBrands() {
        if (App.hasNetwork()) {
            interactor.getBrands(this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }

    override fun getStates() {
        if (App.hasNetwork()) {
            interactor.getStates(this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }

    override fun getCities(state_id: String) {
        if (App.hasNetwork()) {
            interactor.getCities(state_id,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }    }


















    override fun updateDeviceId(updateDeviceId: UpdateDeviceId) {
        updateDeviceId?.let { homeActivityView?.onUpdateDeviceIdViewSuccess(it) }    }

    override fun updateLatLng(updateLatLng: UpdateLatLng) {
        updateLatLng?.let { homeActivityView?.onUpdateLatLngViewSuccess(it) }    }

    override fun onHomeListingsSucces(homeListings: HomeListings) {
        homeListings?.let { homeListingsView?.onHomeListingsViewSuccess(it) }
    }

    override fun onProductDetailSucces(productDetail: ProductDetailModel) {
        productDetail?.let { productDetailView?.onProductDetailViewSuccess(it) }    }

    override fun onAddCartSucces(addCart: AddCart, screen: String) {
        if (screen.equals(PRODUCT_DETAIL))
          addCart?.let { productDetailView?.onAddCartViewSuccess(it) }
        else if (screen.equals(HOME))
            addCart?.let { homeListingsView?.onAddCartViewSuccess(it) }
        else if (screen.equals(ALLOPATHIC_TYPE))
            addCart?.let { allopathicView?.onAddCartViewSuccess(it) }

    }

    override fun onGetCartSucces(screen : String, getCart: GetCart) {
        if (screen.equals(HOME))
        getCart?.let { homeListingsView?.onGetCartViewSuccess(screen, it) }
        else
        getCart?.let { getCartView?.onGetCartViewSuccess(screen, it) }
    }


    override fun onDelProducCartSucces(delProductCart: DelProductCart) {
        delProductCart?.let { getCartView?.onDelProducCartViewSuccess(it) }     }

    override fun onContactUsSucces(contactus: Contactus) {
        contactus?.let { contactUsView?.onContactUsViewSuccess(it) }    }

    override fun onAddEnquireSucces(addEnquire: AddEnquire) {
        addEnquire?.let { addEnquireView?.onAddEnquireViewSuccess(it) }       }


    override fun onOrderPlacedSucces(orderPlaced: OrderPlacedModel) {
       orderPlaced?.let { orderPlacedView?.onOrderPlacedViewSuccess(it) }     }

    override fun addProductSucces(addProduct: AddProduct) {
        addProduct?.let { addProductView?.onAddProductViewSuccess(it) }    }

    override fun logout(logout: Logout) {
        logout?.let { homeActivityView?.onLogoutViewSuccess(it) }      }

    override fun onShowAllProductSuccess(showAllProduct: ShowAllProduct) {
        showAllProduct?.let { showAllProductView?.onShowAllProductViewSuccess(it) }      }

    override fun onDeleteProductSuccess(delProduct: DeleteProduct) {
        delProduct?.let { showAllProductView?.onDelProductViewSuccess(it) }     }

    override fun onChangeOrderStatusSuccess(screen : String, changeOrderStatus: ChangeOrderStatus) {
        if (screen.equals(ORDER_RECEIVED))
            changeOrderStatus?.let { orderReceivedView?.onChangeOrderStatusViewSuccess(it) }
        else if (screen.equals(ORDERS_PLACED))
            changeOrderStatus?.let { orderPlacedView?.onChangeOrderStatusViewSuccess(it) }
    }

    override fun oneditDocNumSuccess(editDocNumber: EditDocNumber) {
        editDocNumber?.let { orderReceivedView?.oneditDocNumViewSuccess(it) }       }

    override fun onUpdateProductSuccess(updateProduct: UpdateProduct) {
        updateProduct?.let { addProductView?.onUpdateProductViewSuccess(it) }    }

    override fun onBankDetailsSuccess(bankDeatilsModel: BankDeatilsModel) {
        bankDeatilsModel?.let { bankDetailsView?.onBankDeatilsViewSuccess(it) }     }

    override fun onAddCardDetailsSuccess(addCard: AddCard) {
        addCard?.let { bankDetailsView?.onAddCardDetailsViewSuccess(it) }     }

    override fun onGetSubscriptionSuccess(getSubscription: GetSubscription) {
        getSubscription?.let { subscriptionView?.onGetSubscriptionViewSuccess(it) }    }

    override fun onBuySubscriptionSuccess(buySubscription: BuySubscription) {
        buySubscription?.let { subscriptionView?.onBuySubscriptionViewSuccess(it) }    }

    override fun onTotalCountSuccess(totalCount: TotalCount) {
        totalCount?.let { myAccountView?.onTotalCountViewSuccess(it) }      }

    override fun onResponsesSuccess(responses: Responses) {
        responses?.let { responsesView?.onResponsesViewSuccess(it) }       }

    override fun onAllopathicSuccess(allopathicModel: AllopathicModel) {
        allopathicModel?.let { allopathicView?.onAllopathicViewSuccess(it) }    }

    override fun onEditCartSuccess(editCartModel: EditCartModel) {
        editCartModel?.let { getCartView?.onEditCartViewSuccess(it) }     }

    override fun onGetProfileSuccess(screen : String, getProfile: GetProfile) {
        if (screen.equals(HOME_ACTIVITY))
           getProfile?.let { homeActivityView?.onGetProfileViewSuccess(it) }
        else if (screen.equals(PROFILE_TYPE))
           getProfile?.let { profileView?.onGetProfileViewSuccess(it) }
        else
           getProfile?.let { checkoutView?.onGetProfileViewSuccess(it) }
    }

    override fun onPaymentSuccess(paymentDetailsModel: PaymentDetailsModel) {
        paymentDetailsModel?.let { checkoutView?.onPaymentViewSuccess(it) }       }

    override fun onUpdateProfileSuccess(updateProfile: UpdateProfile) {
        updateProfile?.let { profileView?.onUpdateProfileSuccess(it) }    }

    override fun onGetBrandsSuccess(getBrands: GetBrands) {
        getBrands?.let { allopathicView?.onGetBrandsViewSuccess(it) }      }

    override fun onGetStatesSuccess(getStates: GetStates) {
        getStates?.let { allopathicView?.onGetStatesViewSuccess(it) }      }

    override fun onGetCitiesSuccess(getCities: GetCities) {
        getCities?.let { allopathicView?.onGetCitiesViewSuccess(it) }      }


    override fun onFailure(error: String) {

        if(homeListingsView!=null)
            homeListingsView?.onFailure(error)
        else if(productDetailView!=null)
            productDetailView?.onFailure(error)
        else if(getCartView!=null)
            getCartView?.onFailure(error)
        else if(contactUsView!=null)
            contactUsView?.onFailure(error)
        else if(orderPlacedView!=null)
            orderPlacedView?.onFailure(error)
        else if(addProductView!=null)
            addProductView?.onFailure(error)
        else if(homeActivityView!=null)
            homeActivityView?.onFailure(error)
        else if(addEnquireView!=null)
            addEnquireView?.onFailure(error)
        else if(showAllProductView!=null)
            showAllProductView?.onFailure(error)
        else if(orderReceivedView!=null)
            orderReceivedView?.onFailure(error)
        else if(bankDetailsView!=null)
            bankDetailsView?.onFailure(error)
        else if(subscriptionView!=null)
            subscriptionView?.onFailure(error)
        else if(myAccountView!=null)
            myAccountView?.onFailure(error)
        else if(responsesView!=null)
            responsesView?.onFailure(error)
        else if(allopathicView!=null)
            allopathicView?.onFailure(error)
        else if(checkoutView!=null)
            checkoutView?.onFailure(error)
        else if(profileView!=null)
            profileView?.onFailure(error)

    }



}
