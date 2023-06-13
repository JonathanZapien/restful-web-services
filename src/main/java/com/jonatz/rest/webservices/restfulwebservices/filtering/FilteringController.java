package com.jonatz.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.List;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

  @GetMapping("/filtering")
  public SomeBean filtering() {
    return new SomeBean("value1", "value2", "value3");
  }

  @GetMapping("/filtering-list")
  public List<SomeBean> filteringList() {
    return List.of(
        new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));
  }

  @GetMapping("/filtering-mapping-jackson")
  public MappingJacksonValue filteringMappingJackson() {
    SomeBean2 someBean = createSomeBean2();
    SimpleBeanPropertyFilter filter = createFieldFilter("field1", "field3");
    SimpleFilterProvider filters = createFilterProvider(filter);
    return createMappingJacksonValue(someBean, filters);
  }

  @GetMapping("/filtering-list-mapping-jackson")
  public MappingJacksonValue filteringListMappingJackson() {

    List<SomeBean2> beans = List.of(
        new SomeBean2("value1", "value2", "value3"), new SomeBean2("value4", "value5", "value6"));
    SimpleBeanPropertyFilter filter = createFieldFilter("field2");
    SimpleFilterProvider filters = createFilterProvider(filter);
    return createMappingJacksonValue(beans, filters);
  }

  private SomeBean2 createSomeBean2() {
    return new SomeBean2("value1", "value2", "value3");
  }

  private SimpleBeanPropertyFilter createFieldFilter(String... fields) {
    return SimpleBeanPropertyFilter.filterOutAllExcept(fields);
  }

  private SimpleFilterProvider createFilterProvider(SimpleBeanPropertyFilter filter) {
    SimpleFilterProvider filters = new SimpleFilterProvider();
    filters.addFilter("SomeBeanFilter", filter);
    return filters;
  }

  private MappingJacksonValue createMappingJacksonValue(
      Object bean, SimpleFilterProvider filters) {
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
    mappingJacksonValue.setFilters(filters);
    return mappingJacksonValue;
  }
}
