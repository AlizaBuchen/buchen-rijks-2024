package buchen.rijks;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RijksService {
    @GET("/api/en/collection")
    Observable<ArtObjects> page(
            @Query("apikey") String key,
            @Query("p") int page
    );

    @GET("/api/en/collection")
    Observable<ArtObjects> query(
            @Query("apikey") String key,
            @Query("p") int page,
            @Query("q") String query
    );

    @GET("/api/en/collection")
    Observable<ArtObjects> artist(
            @Query("apikey") String key,
            @Query("p") int page,
            @Query("involvedMaker") String artist

    );
}