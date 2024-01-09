package com.develofer.randomusers.ui.userdetail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.develofer.randomusers.R
import com.develofer.randomusers.databinding.FragmentUserDetailBinding
import com.develofer.randomusers.domain.data.UserDomain
import com.develofer.randomusers.utils.CustomTypefaceSpan
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment: Fragment(), OnMapReadyCallback {

    private val args: UserDetailFragmentArgs by navArgs()
    private var binding: FragmentUserDetailBinding? = null
    private var user: UserDomain? = null
    private lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        this.user = args.user

        setUpView()
        setUpMap(savedInstanceState)

        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
        binding = null
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun setUpView() {
        setUpToolbar()
        setUpStatusBar()
        setUpTexts()
        setUpImage()
    }

    private fun setUpToolbar() {
        val toolbar = binding?.tbFragmentUserDetailToolbar
        toolbar?.setupWithNavController(navController = findNavController())
        toolbar?.title = createSpannableString()
    }

    private fun createSpannableString(): SpannableString {
        val title = getString(
            R.string.user_list_adapter__user_name_and_surname,
            user?.name?.first?.uppercase(),
            user?.name?.last?.uppercase()
        )
        val customTypeface = Typeface.createFromAsset(context?.assets, "oswald_medium.ttf")
        val spannableString = SpannableString(title)
        spannableString.setSpan(
            CustomTypefaceSpan("", customTypeface),
            0,
            spannableString.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(Color.WHITE),
            0,
            spannableString.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        return spannableString
    }

    private fun setUpStatusBar() {
        val windowInsetsController =
            activity?.window?.let {
                WindowCompat.getInsetsController(it, it.decorView)
            }
        windowInsetsController?.apply {
            isAppearanceLightStatusBars = false
        }
    }

    private fun setUpTexts() {
        binding?.apply {
            mapView = fragmentFragmentUserDetailMap
            tvFragmentUserDetailUserName.text = getString(
                R.string.fragment_user_detail__complete_name,
                user?.name?.title,
                user?.name?.first,
                user?.name?.last
            )
            tvFragmentUserDetailUserEmail.text = user?.email
            tvFragmentUserDetailUserGender.text = user?.gender
            tvFragmentUserDetailUserPhone.text = user?.phone
            tvFragmentUserDetailUserRegisterDate.text = user?.registered?.date?.substring(0, 10)
        }
    }

    private fun setUpImage() {
        context?.let {
            binding?.apply {
                Glide.with(it)
                    .load(user?.picture?.large)
                    .into(ivFragmentUserDetailUserImage)
            }
        }
    }

    private fun setUpMap(savedInstanceState: Bundle?) {
        binding?.fragmentFragmentUserDetailMap?.let {
            it.onCreate(savedInstanceState)
            it.getMapAsync(this)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        val targetLocation = LatLng(
            user?.location?.coordinates?.latitude.orEmpty().toDouble(),
            user?.location?.coordinates?.longitude.orEmpty().toDouble()
        )
        googleMap = map
        googleMap.addMarker(MarkerOptions().position(targetLocation).title("Mi Marcador"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(targetLocation, 7f))
    }

}