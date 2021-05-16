package ro.fastrackit.trainsApp.repository.train;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import ro.fastrackit.trainsApp.model.TrainFilters;
import ro.fastrackit.trainsApp.model.entity.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainDao {

    private final MongoTemplate mongo;

    public Page<Train> getAllWithFilters(TrainFilters filters) {
        System.out.println(filters.getMinCarts());
        List<Criteria> criterias = new ArrayList<>();
        Optional.ofNullable(filters.getModel())
                .ifPresent(element -> criterias.add(Criteria.where("model").is(element)));
        Optional.ofNullable(filters.getLocation())
                .ifPresent(element -> criterias.add(Criteria.where("location").is(element)));
        Optional.ofNullable(filters.getMinCarts())
                .ifPresent(element -> criterias.add(Criteria.where("carts").gt(Integer.parseInt(element))));
        Optional.ofNullable(filters.getMaxCarts())
                .ifPresent(element -> criterias.add(Criteria.where("carts").lt(Integer.parseInt(element))));

        Criteria criteria = new Criteria().andOperator(criterias.toArray(new Criteria[0]));
        Pageable pageable = PageRequest.of(0, 10, Sort.by("carts"));

        Query query = Query.query(criteria);

        List<Train> trains = getAllTrainsWithCriteria(criterias, query);

        return PageableExecutionUtils.getPage(
                trains,
                pageable,
                () -> getNumberPages(criterias, criteria));
    }

    List<Train> getAllTrainsWithCriteria(List<Criteria> criterias, Query query) {
        List<Train> trains;
        if (criterias.isEmpty()) {
            trains = mongo.findAll(Train.class);
        } else {
            trains = mongo.find(
                    query,
                    Train.class);
        }
        return trains;
    }

    Long getNumberPages(List<Criteria> criterias, Criteria criteria) {
        long pagesNumber;
        if (criterias.isEmpty()) {
            pagesNumber = mongo.findAll(Train.class).size();
        } else {
            pagesNumber = mongo.count(Query.query(criteria), Train.class);
        }
        return pagesNumber;
    }
}
