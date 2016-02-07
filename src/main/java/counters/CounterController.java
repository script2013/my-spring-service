package counters;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping (value = "/counters", produces = MediaType.APPLICATION_JSON_VALUE)
public class CounterController {

    private final Map<String, Set<String>> page2Users = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public List<PageCount> counters() {
        List<PageCount> list = new ArrayList<>();
        for(Map.Entry<String, Set<String>> kvs: page2Users.entrySet()){
            list.add(new PageCount(kvs.getKey(), kvs.getValue().size()));
        }
        return list;
    }

    /*an alternative
    @RequestMapping(value="/{pageId}", method=RequestMethod.GET)
    public PageCount getCounter(@PathVariable String pageId) {
        if (page2Users.containsKey(pageId)){
            return new PageCount(pageId, page2Users.get(pageId).size());
        }
        else{
            return new PageCount(pageId, 0);
        }
    }
    */

    @RequestMapping(value="/view", method=RequestMethod.GET)
    public PageCount getCounter(@RequestParam(value="pageId") String pageId) {
        if (page2Users.containsKey(pageId)){
            return new PageCount(pageId, page2Users.get(pageId).size());
        }
        else{
            return new PageCount(pageId, 0);
        }
    }

    @RequestMapping(value="/inc", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Output> incrementPageCount(@RequestBody UserPagePair userPagePair) {
        synchronized (page2Users){
            if (!page2Users.containsKey(userPagePair.getPageId())){
                page2Users.put(userPagePair.getPageId(), new HashSet<>());
            }
            page2Users.get(userPagePair.getPageId()).add(userPagePair.getUserId());
            return new ResponseEntity<>(new Output("accepted"), HttpStatus.OK);
        }
    }

}


