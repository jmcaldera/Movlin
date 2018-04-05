package com.jmcaldera.movlin.people

import com.jmcaldera.domain.functional.asError
import com.jmcaldera.domain.functional.result
import com.jmcaldera.domain.model.NotFoundError
import com.jmcaldera.domain.model.Person
import com.jmcaldera.domain.model.UnauthorizedError
import com.jmcaldera.domain.usecase.GetPersonDetailsUseCase
import com.jmcaldera.movlin.model.mapper.convertPersonFromDomain
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jmcaldera on 04-04-18.
 */
@RunWith(MockitoJUnitRunner::class)
class PeoplePresenterTest {

    companion object {
        const val ID = 1
    }

    @Mock
    private lateinit var getPersonDetailsUseCase: GetPersonDetailsUseCase

    @Mock
    private lateinit var view : PeopleContract.View

    private lateinit var person: Person

    private lateinit var presenter: PeoplePresenter

    @Before
    fun setUp() = runBlocking {
        person = Person(1, "name", "1970", null, "place",
                "bio", emptyList(), "/path")

        `when`(view.isActive()).thenReturn(true)

        `when`(getPersonDetailsUseCase.execute(GetPersonDetailsUseCase.Params(ID)))
                .thenReturn(person.result())

        presenter = PeoplePresenter(getPersonDetailsUseCase)
        presenter.view = view
    }

    @Test
    fun loadPersonDetails_showIntoView() = runBlocking {
        presenter.loadPersonDetails(ID)

        verify(getPersonDetailsUseCase).execute(GetPersonDetailsUseCase.Params(ID))
        verify(view).showDetails(convertPersonFromDomain(person))
    }

    @Test
    fun loadPersonDetails_showUnauthorizedErrorInView() = runBlocking {

        `when`(getPersonDetailsUseCase.execute(GetPersonDetailsUseCase.Params(ID)))
                .thenReturn(UnauthorizedError().asError())

        presenter.loadPersonDetails(ID)

        verify(getPersonDetailsUseCase).execute(GetPersonDetailsUseCase.Params(ID))

        verify(view).showUnauthorizedError()
    }

    @Test
    fun loadPersonDetails_showNotFoundErrorInView() = runBlocking {

        `when`(getPersonDetailsUseCase.execute(GetPersonDetailsUseCase.Params(ID)))
                .thenReturn(NotFoundError().asError())

        presenter.loadPersonDetails(ID)

        verify(getPersonDetailsUseCase).execute(GetPersonDetailsUseCase.Params(ID))

        verify(view).showNotFoundError()
    }
}