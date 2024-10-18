package moum.project.controller;

import moum.project.service.CollectionService;
import moum.project.vo.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/collection")
public class CollectionController {

  CollectionService collectionService;

  public CollectionController(CollectionService collectionService) {
    this.collectionService = collectionService;
  }

  @PostMapping("add")
  public String add(Collection collection) throws Exception {
    System.out.println("service 호출 전");
    collectionService.add(collection);
    System.out.println("service 호출 후");
    return "redirect:/myHome";
  }
}
