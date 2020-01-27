package com.example.medideals.ui.activities.home

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
import com.example.medideals.ui.activities.home.allProducts.AllProducts_Adapter
import com.example.passerby.ui.baseClasses.BaseContract

interface HomeContract : BaseContract
{

    interface HomePresenter
    {
        fun homeListing()
        fun getCart(screen : String)
        fun productDetail(product_id : String)
        fun delProductCartDetail(product_id : String)
        fun addCart(product_id : String,quantity : String, screen : String)
        fun contactUs(name : String,email : String, phone : String, title : String, message : String)
        fun addEnquire(vendor_id : String,vendor_email : String, message : String)
        fun orderPlaced(vendor_id : String,page : String)
        fun addProduct(addPlacedModel: AddProductModel)
        fun updateDeviceID()
        fun updateLatLng(lat : String , lng : String)
        fun logout()
        fun showAllProduct(page : String)
        fun deleteProduct(prod_id : String)
        fun changeOrderStatus(screen : String, list_id : String, status : String)
        fun editDocNum(list_id : String, docNum : String)
        fun updateProduct(saveProductInf: SaveProductInf)
        fun bankDetails()
        fun addCardDetails(addCardInfo: AddCardInfo)
        fun getSubscription()
        fun buySubscription(subscription_id: String)
        fun totalCount()
        fun responses()
        fun allopathic(cat_id : String)
        fun editCart(record : String)
        fun getProfile(screen : String)
        fun payment(paymentDetails: PaymentDetails)
        fun updateProfile(saveProfileData: SaveProfileData)
        fun getBrands()
        fun getStates()
        fun getCities(state_id : String)
    }

    interface HomeInteractor
    {
        fun homeListing(callback: OnHomeCompleteListener)
        fun productDetail(product_id : String, callback: OnHomeCompleteListener)
        fun delProductCartDetail(product_id : String, callback: OnHomeCompleteListener)
        fun addCart(product_id : String,quantity : String, screen : String, callback: OnHomeCompleteListener)
        fun getCart(screen : String, callback: OnHomeCompleteListener)
        fun contactUs(name : String,email : String, phone : String, title : String, message : String, callback: OnHomeCompleteListener)
        fun addEnquire(vendor_id : String,vendor_email : String, message : String, callback: OnHomeCompleteListener)
        fun orderPlaced(vendor_id : String,page : String, callback: OnHomeCompleteListener)
        fun addProduct(addPlacedModel: AddProductModel, callback: OnHomeCompleteListener)
        fun updateDeviceID(callback: OnHomeCompleteListener)
        fun updateLatLng(lat : String , lng : String, callback: OnHomeCompleteListener)
        fun logout(callback: OnHomeCompleteListener)
        fun showAllProduct(page : String, callback: OnHomeCompleteListener)
        fun deleteProduct(prod_id : String, callback: OnHomeCompleteListener)
        fun changeOrderStatus(screen : String, list_id : String, status : String, callback: OnHomeCompleteListener)
        fun editDocNum(list_id : String, docNum : String, callback: OnHomeCompleteListener)
        fun updateProduct(saveProductInf: SaveProductInf, callback: OnHomeCompleteListener)
        fun bankDetails(callback: OnHomeCompleteListener)
        fun addCardDetails(addCardInfo: AddCardInfo, callback: OnHomeCompleteListener)
        fun getSubscription(callback: OnHomeCompleteListener)
        fun buySubscription(subscription_id: String, callback: OnHomeCompleteListener)
        fun totalCount(callback: OnHomeCompleteListener)
        fun responses(callback: OnHomeCompleteListener)
        fun allopathic(cat_id : String, callback: OnHomeCompleteListener)
        fun editCart(record : String, callback: OnHomeCompleteListener)
        fun getProfile(screen : String, callback: OnHomeCompleteListener)
        fun payment(paymentDetails: PaymentDetails, callback: OnHomeCompleteListener)
        fun updateProfile(saveProfileData: SaveProfileData, callback: OnHomeCompleteListener)
        fun getBrands(callback: OnHomeCompleteListener)
        fun getStates(callback: OnHomeCompleteListener)
        fun getCities(state_id : String, callback: OnHomeCompleteListener)

    }


    interface OnHomeCompleteListener : BaseContract.BaseOnCompleteListener
    {
        fun onHomeListingsSucces(homeListings: HomeListings)
        fun onProductDetailSucces(productDetail: ProductDetailModel)
        fun onAddCartSucces(addCart: AddCart,screen : String)
        fun onGetCartSucces(screen : String,getCart: GetCart)
        fun onDelProducCartSucces(delProductCart: DelProductCart)
        fun onContactUsSucces(contactus: Contactus)
        fun onAddEnquireSucces(addEnquire: AddEnquire)
        fun onOrderPlacedSucces(orderPlaced: OrderPlacedModel)
        fun addProductSucces(addProduct: AddProduct)
        fun updateDeviceId(updateDeviceId: UpdateDeviceId)
        fun updateLatLng(updateLatLng: UpdateLatLng)
        fun logout(logout: Logout)
        fun onShowAllProductSuccess(showAllProduct: ShowAllProduct)
        fun onDeleteProductSuccess(delProduct: DeleteProduct)
        fun onChangeOrderStatusSuccess(screen : String, changeOrderStatus: ChangeOrderStatus)
        fun oneditDocNumSuccess(editDocNumber: EditDocNumber)
        fun onUpdateProductSuccess(updateProduct: UpdateProduct)
        fun onBankDetailsSuccess(bankDeatilsModel: BankDeatilsModel)
        fun onAddCardDetailsSuccess(addCard: AddCard)
        fun onGetSubscriptionSuccess(getSubscription: GetSubscription)
        fun onBuySubscriptionSuccess(buySubscription: BuySubscription)
        fun onTotalCountSuccess(totalCount: TotalCount)
        fun onResponsesSuccess(responses: Responses)
        fun onAllopathicSuccess(allopathicModel: AllopathicModel)
        fun onEditCartSuccess(editCartModel: EditCartModel)
        fun onGetProfileSuccess(screen : String, getProfile: GetProfile)
        fun onPaymentSuccess(paymentDetailsModel: PaymentDetailsModel)
        fun onUpdateProfileSuccess(updateProfile: UpdateProfile)
        fun onGetBrandsSuccess(getBrands: GetBrands)
        fun onGetStatesSuccess(getStates: GetStates)
        fun onGetCitiesSuccess(getCities: GetCities)
    }

    interface HomeListingsView : BaseContract.BaseView
    {
        fun onHomeListingsViewSuccess(homeListings: HomeListings)
        fun onAddCartViewSuccess(addCart: AddCart)
        fun onGetCartViewSuccess(screen : String, getCart: GetCart)
    }
    interface HomeActivityView : BaseContract.BaseView
    {
        fun onUpdateDeviceIdViewSuccess(updateDeviceId: UpdateDeviceId)
        fun onUpdateLatLngViewSuccess(updateLatLng: UpdateLatLng)
        fun onLogoutViewSuccess(logout: Logout)
        fun onGetProfileViewSuccess(getProfile: GetProfile)
    }

    interface ProductDetailView : BaseContract.BaseView
    {
        fun onProductDetailViewSuccess(productDetail: ProductDetailModel)
        fun onAddCartViewSuccess(addCart: AddCart)
    }
    interface GetCartView : BaseContract.BaseView
    {
        fun onGetCartViewSuccess(screen : String, getCart: GetCart)
        fun onDelProducCartViewSuccess(delProductCart: DelProductCart)
        fun onEditCartViewSuccess(editCartModel: EditCartModel)
    }

    interface ContactUsView : BaseContract.BaseView
    {
        fun onContactUsViewSuccess(contactus: Contactus)
    }

    interface AddEnquireView : BaseContract.BaseView
    {
        fun onAddEnquireViewSuccess(addEnquire: AddEnquire)
    }

    interface OrderPlacedView : BaseContract.BaseView
    {
        fun onOrderPlacedViewSuccess(orderPlaced: OrderPlacedModel)
        fun onChangeOrderStatusViewSuccess(changeOrderStatus: ChangeOrderStatus)
    }

    interface OrderReceivedView : BaseContract.BaseView
    {
        fun onChangeOrderStatusViewSuccess(changeOrderStatus: ChangeOrderStatus)
        fun oneditDocNumViewSuccess(editDocNumber: EditDocNumber)
    }

    interface AddProductView : BaseContract.BaseView
    {
        fun onAddProductViewSuccess(addProduct: AddProduct)
        fun onUpdateProductViewSuccess(updateProduct: UpdateProduct)
    }

    interface ShowAllProductView : BaseContract.BaseView
    {
        fun onShowAllProductViewSuccess(showAllProduct: ShowAllProduct)
        fun onDelProductViewSuccess(delProduct: DeleteProduct)
    }

    interface BankDeatilsView : BaseContract.BaseView
    {
        fun onBankDeatilsViewSuccess(bankDeatilsModel: BankDeatilsModel)
        fun onAddCardDetailsViewSuccess(addCard: AddCard)
    }

    interface SubscriptionView : BaseContract.BaseView
    {
        fun onGetSubscriptionViewSuccess(getSubscription: GetSubscription)
        fun onBuySubscriptionViewSuccess(buySubscription: BuySubscription)
    }

    interface MyAccountView : BaseContract.BaseView
    {
        fun onTotalCountViewSuccess(totalCount: TotalCount)
    }

    interface ResponsesView : BaseContract.BaseView
    {
        fun onResponsesViewSuccess(responses: Responses)
    }

    interface AllopathicView : BaseContract.BaseView
    {
        fun onAllopathicViewSuccess(allopathicModel: AllopathicModel)
        fun onAddCartViewSuccess(addCart: AddCart)
        fun onGetBrandsViewSuccess(getBrands: GetBrands)
        fun onGetStatesViewSuccess(getStates: GetStates)
        fun onGetCitiesViewSuccess(getCities: GetCities)
    }

    interface CheckoutView : BaseContract.BaseView
    {
        fun onGetProfileViewSuccess(getProfile: GetProfile)
        fun onPaymentViewSuccess(paymentDetailsModel: PaymentDetailsModel)
    }

    interface ProfileView : BaseContract.BaseView
    {
        fun onUpdateProfileSuccess(updateProfile: UpdateProfile)
        fun onGetProfileViewSuccess(getProfile: GetProfile)
    }

}