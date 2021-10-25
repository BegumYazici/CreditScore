package com.begicim.clearscoredemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.begicim.clearscoredemo.network.CreditApiService
import com.begicim.clearscoredemo.network.CreditModel
import com.begicim.clearscoredemo.network.CreditReport
import com.begicim.clearscoredemo.ui.ApiStatus
import com.begicim.clearscoredemo.ui.CreditViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify

class CreditViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var creditApiService = Mockito.mock(CreditApiService::class.java)

    private val dispatcher = TestCoroutineDispatcher()

    private val creditViewModel = CreditViewModel(creditApiService, dispatcher)

    @Test
    fun `when loadCreditScore() is called api service should be called`() {
        creditViewModel.loadCreditScore()

        verify(creditApiService).getCreditInfo()
    }

    @Test
    fun `when loadCreditScore() is called api service should be Success`() = runBlockingTest {
        val dummyServiceResponse = CreditModel(CreditReport(120, 500, 4), "Success")

        given(creditApiService.getCreditInfo()).willReturn(CompletableDeferred(dummyServiceResponse))

        creditViewModel.loadCreditScore()

        assertEquals(ApiStatus.SUCCESS, creditViewModel.apiStatus.value)
    }

    @Test
    fun `when loadCreditScore() is called api service should be error`() {
        given(creditApiService.getCreditInfo()).willThrow(IllegalArgumentException::class.java)

        creditViewModel.loadCreditScore()

        assertEquals(ApiStatus.ERROR, creditViewModel.apiStatus.value)
    }

    @Test
    fun `when loadCreditScore() is called progress value should be calculate correct`() {
        val dummyServiceResponse = CreditModel(CreditReport(120, 500, 4), "Success")

        given(creditApiService.getCreditInfo()).willReturn(CompletableDeferred(dummyServiceResponse))

        creditViewModel.loadCreditScore()

        val expectedValue = (dummyServiceResponse.creditReportInfo.score * 100) / (dummyServiceResponse.creditReportInfo.maxScoreValue)

        assertEquals(expectedValue, creditViewModel.creditScore.value?.progressValue)
    }
}