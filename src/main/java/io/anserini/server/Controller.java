/*
 * Anserini: A Lucene toolkit for reproducible information retrieval research
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.anserini.server;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class Controller {

  private static final String DEFAULT_COLLECTION = "msmarco-v1-passage";

  @RequestMapping(method = RequestMethod.GET, path = {"/collection/{collection}/search", "/search"})
  public List<QueryResult> search(@PathVariable(value = "collection", required = false) String collection,
      @RequestParam("query") String query) {

    if (collection == null) {
      collection = DEFAULT_COLLECTION;
    }

    SearchService searchService = new SearchService(collection);
    return searchService.search(query, 10);
  }

}