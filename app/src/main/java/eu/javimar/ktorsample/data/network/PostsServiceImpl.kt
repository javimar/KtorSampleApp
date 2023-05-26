package eu.javimar.ktorsample.data.network

import eu.javimar.ktorsample.data.dto.PostRequest
import eu.javimar.ktorsample.data.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.URLProtocol
import io.ktor.http.path

class PostsServiceImpl(
    private val ktorClient: HttpClient
): PostsService {

    override suspend fun getPosts(): List<PostResponse> {
        return try {
            /*
                Could use abreviated way
                ktorClient.get("jsonplaceholder.typicode.com/posts")
            */
            val result = ktorClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "jsonplaceholder.typicode.com"
                    path("posts")
//                    parameters.append("token", "123")
                }
            }
            result.body()

        } catch (e: RedirectResponseException) {
            // 3xx responses
            println("Error: ${e.response.status.description}")
            emptyList()
        }
        catch (e: ClientRequestException) {
            // 4xx responses
            println("Error: ${e.response.status.description}")
            emptyList()
        }
        catch (e: ServerResponseException) {
            // 5xx responses
            println("Error: ${e.response.status.description}")
            emptyList()
        }
        catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            ktorClient.post {
                url {
                    // To be completed
                }
            }.body()

        } catch (e: RedirectResponseException) {
            // 3xx responses
            println("Error: ${e.response.status.description}")
            null
        }
        catch (e: ClientRequestException) {
            // 4xx responses
            println("Error: ${e.response.status.description}")
            null
        }
        catch (e: ServerResponseException) {
            // 5xx responses
            println("Error: ${e.response.status.description}")
            null
        }
        catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

}