package edu.mit.civic.mediacloud.test.where;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bericotech.clavin.resolver.ResolvedLocation;

import edu.mit.civic.mediacloud.ParseManager;

/**
 * Tests that verify against a small hand-coded corpus of articles.  These check that the
 * list of extracted places contains the hand-coded one. 
 */
public class HandCodedDisambiguationTest {
 
    private static final Logger logger = LoggerFactory.getLogger(HandCodedDisambiguationTest.class);

    @Test
    public void testMentionsHandCodedCountry() throws Exception {
        List<CodedArticle> articles;
        articles = TestUtils.loadExamplesFromFile(TestUtils.NYT_JSON_PATH);
        assertEquals(23, articles.size());
        verifyArticlesMentionHandCodedCountry(articles);
        articles = TestUtils.loadExamplesFromFile(TestUtils.HUFFPO_JSON_PATH);
        assertEquals(24, articles.size());
        verifyArticlesMentionHandCodedCountry(articles);
        articles = TestUtils.loadExamplesFromFile(TestUtils.BBC_JSON_PATH);
        assertEquals(24, articles.size());
        verifyArticlesMentionHandCodedCountry(articles);
    }

    private void verifyArticlesMentionHandCodedCountry(List<CodedArticle> articles) throws Exception{
        for(CodedArticle article: articles){
            logger.info("Testing article "+article.mediacloudId+" (looking for "+article.handCodedPlaceName+" / "+article.handCodedCountryCode+")");
            List<ResolvedLocation> resolvedLocations = ParseManager.extractAndResolve(article.text).getResolvedLocations();
            assertTrue("Didn't find "+article.handCodedPlaceName+" ("+article.handCodedCountryCode+") in article "+article.mediacloudId,
                    article.mentionsHandCodedCountry(resolvedLocations));
        }
    }

}