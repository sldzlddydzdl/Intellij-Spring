package com.sp.fc.web.controller;


import com.sp.fc.web.config.CustomSecurityTag;
import com.sp.fc.web.service.Paper;
import com.sp.fc.web.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/paper")
@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;

//    @PreAuthorize("isStudent()")
    // enum 을 가져올뗀 T() 를 통해서 가져와야 한다.
//    @PostFilter("filterObject.state != T(com.sp.fc.web.service.Paper.State).PREPARE")
//    @PostFilter("notPrepareState(filterObject) && filterObject.studentIds.contains(#user.username)")
    @GetMapping("/mypapers")
    public List<Paper> myPapers(@AuthenticationPrincipal User user){
        return paperService.getMyPapers(user.getUsername());
    }

    @Secured({"ROLE_USER" , "RUN_AS_PRIMARY"})
    @GetMapping("/allpapers")
    public List<Paper> allpapers(@AuthenticationPrincipal User user){
        return paperService.getAllPapers();
    }

//    @CustomSecurityTag({"SCHOOL_PRIMARY"}) // 이 Security 를 SCHOOL_PRIMARY 인 경우에는 인 메소드를 쓸수 있다. 아닌경우에는 쓸수 없어요
    @CustomSecurityTag("SCHOOL_PRIMARY") // 이 Security 를 SCHOOL_PRIMARY 인 경우에는 인 메소드를 쓸수 있다. 아닌경우에는 쓸수 없어요
    @GetMapping("/getPapersByPrimary")
    public List<Paper> getPapersByPrimary(@AuthenticationPrincipal User user){
        return paperService.getAllPapers();
    }

//    @PreAuthorize("hasPermission(#paperId, 'paper', 'read')")
    @PostAuthorize("returnObject.studentIds.contains(principal.username)")
    @GetMapping("/get/{paperId}")
    public Paper getPaper(@AuthenticationPrincipal User user, @PathVariable Long paperId){
        return paperService.getPaper(paperId);
    }





}