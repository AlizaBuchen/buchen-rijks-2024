package buchen.rijks;

import com.andrewoid.ApiKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RijksServiceTest {

    @Test
    public void page() {

        // given
        RijksService service = new RijksServiceFactory().create();
        ApiKey apiKey = new ApiKey();

        // when
        ArtObjects collection = service.page(
                apiKey.get(), 1
        ).blockingGet();

        // then
        ArtObject artObject = collection.artObjects[0];
        assertNotNull(artObject.title);
        assertNotNull(artObject.longTitle);
        assertNotNull(artObject.webImage);
        assertNotNull(artObject.webImage.url);
        assertNotNull(artObject.principalOrFirstMaker);
    }

    @Test
    public void query() {

        // given
        RijksService service = new RijksServiceFactory().create();
        ApiKey apiKey = new ApiKey();

        // when
        ArtObjects collection = service.query(
                apiKey.get(), 1, "e"
        ).blockingGet();

        // then
        ArtObject artObject = collection.artObjects[0];
        assertNotNull(artObject.title);
        assertNotNull(artObject.longTitle);
        assertNotNull(artObject.webImage);
        assertNotNull(artObject.webImage.url);
        assertNotNull(artObject.principalOrFirstMaker);
    }

    @Test
    public void artist() {

        // given
        RijksService service = new RijksServiceFactory().create();
        ApiKey apiKey = new ApiKey();

        // when
        ArtObjects collection = service.artist(
                apiKey.get(), 1, "Rembrandt van Rijn"
        ).blockingGet();

        // then
        ArtObject artObject = collection.artObjects[0];
        assertNotNull(artObject.title);
        assertNotNull(artObject.longTitle);
        assertNotNull(artObject.webImage);
        assertNotNull(artObject.webImage.url);
        assertNotNull(artObject.principalOrFirstMaker);
    }
}
