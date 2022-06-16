package com.example.moviezone

import com.example.moviezone.repository.MovieRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mock

class MovieRepositoryTest {

    @Mock
    val movieRepository: MovieRepository = MovieRepository()

    @Test
    fun testMovieById() = runTest {
        movieRepository.getMovieById(222).collectLatest {
            val expectedMovie = "Berlin: Symphony of a Great City"
            assertEquals(expectedMovie, it.title)
        }
    }

    @Test
    fun testUpcomingMovies() = runTest {
        movieRepository.getUpcomingMovies().collectLatest {
            assertTrue(it.movies.isNotEmpty())
        }
    }

    @Test
    fun testNowPlayingMovies() = runTest {
        movieRepository.getNowPlayingMovies().collectLatest {
            assertTrue(it.movies.isNotEmpty())
        }
    }

    @Test
    fun testDiscoverMovies() = runTest {
        movieRepository.getDiscoverMovies().collectLatest {
            assertTrue(it.movies.isNotEmpty())
        }
    }

    @Test
    fun testTopRatedMovies() = runTest {
        movieRepository.getTopRatedMovies().collectLatest {
            assertTrue(it.movies.isNotEmpty())
        }
    }

    @Test
    fun testReviewsByMovieId() = runTest {
        movieRepository.getReviewsById(222).collectLatest {
            // Testing if it's empty because API doesn't have any data attached in form of reviews for movie id 222.
            assertTrue(it.reviews.isEmpty())
        }
    }

    @Test
    fun testSearchMoviesByTitle() = runTest {
        movieRepository.searchMoviesByTitle("Top Gun").collectLatest {
            assertTrue(it.movies.isNotEmpty())
        }
    }

    @Test
    fun testGetCreditsByMovieId() = runTest {
        movieRepository.getCreditsByMovieId(222).collectLatest {
            assertTrue(it.cast.isNotEmpty())
        }
    }

    @Test
    fun testGetMovieTrailerById() = runTest {
        movieRepository.getMovieTrailerById(222).collectLatest {
            // Testing if it is empty because API returns that it doesn't have a video attached to movie by id 222
            assertTrue(it.results.isEmpty())
        }
    }

    @Test
    fun testSOmething() = runTest {
        movieRepository.getSimilarMovies(222).collectLatest {
            assertTrue(it.movies.isNotEmpty())
        }
    }
}