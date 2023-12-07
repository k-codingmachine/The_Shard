package com.shard.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shard.domain.CouponIssuanceVO;
import com.shard.domain.CouponVO;
import com.shard.domain.DeliverAddrVO;
import com.shard.domain.PageVO;
import com.shard.domain.ShardMemberVO;
import com.shard.mapper.MemberMapper;
import com.shard.service.AdminService;
import com.shard.service.MailSendService;
import com.shard.service.SosialLoginService;
import com.shard.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/shard/*")
@Log4j
@RequiredArgsConstructor
public class UserController {

	private final SosialLoginService kakaoLoginService;

	private final UserService userservice;

	private final MailSendService mailSendService;

	private final PasswordEncoder passwordEncoder;

	private final MemberMapper mapper;
	
	private final AdminService adminService;

	// 메인 페이지로 이동 // 카카로 로그아웃 이후에도 여기로 이동
	@GetMapping("")
	public String index() {
		log.info("index");
		return "index";
	}

	// 로그인 페이지로 이동
	@GetMapping("/login")
	public String login(@ModelAttribute("result") String result, Model model) {
		log.info("login");
		model.addAttribute("result", result);
		return "user/shardLogin";
	}

	@GetMapping("/failLogin")
	public String failLogin(RedirectAttributes rttr) {
		rttr.addFlashAttribute("result", "noUser");
		return "redirect:/shard/login";
	}

	// 회원가입 페이지로 이동
	@GetMapping("/join")
	public String join() {
		log.info("join");
		return "user/shardJoin";
	}

	@PostMapping("/myPage")
	@PreAuthorize("isAuthenticated()")
	public String mypage(Model model, @RequestParam String email) {
		model.addAttribute("user", userservice.getUser(email));
		return "user/myPage";
	}

	// 카카오 회원가입 페이지로 이동
	@GetMapping("/kakaoJoin")
	public String kakaoJoin(Model model, @ModelAttribute("userEmail") String userEmail,
			@ModelAttribute("nickName") String nickName) {
		log.info("kakaoJoin");
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("nickName", nickName);
		return "user/kakaoJoin";
	}

	// 카카오로 로그인했을 때 따로 만든 카카오 로그아웃
	@GetMapping("/kakaoLogout")
	public String kakaoLogout(RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response, HttpSession session, SessionStatus status) throws Throwable {
		log.info("카카오 로그아웃");
		session.invalidate();
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		
		return "redirect:/shard/";
	}

	// ajax로 회원가입할 때 데이터베이스에 사용중인 아이디가 있는지 체크
	   @GetMapping("/idCheck")
	   public void idCheck(String userId, HttpServletResponse response) throws Exception {
	      System.out.println(userId);
	      int result = userservice.idCheck(userId);
	      MultiValueMap<String, Object> json = new LinkedMultiValueMap<>();
	      json.add("result", result);
	      response.setContentType("application/json");
	      response.getWriter().write(json.toString());
	   }

	@GetMapping("/emailSecurity")
	public ResponseEntity<String> emailSecurity(String email) {
		System.out.println("요청온 이메일 : " + email);
		return new ResponseEntity<String>(mailSendService.joinEmail(email), HttpStatus.OK);
	}

	// 카카오 로그인을 할 때
	@GetMapping("/login/oauth")
	public String kakaoOauth(@RequestParam(required = false) String code, Model model, RedirectAttributes rttr, HttpSession session)
			throws Throwable {
		String url = "";

		String access_Token = kakaoLoginService.getAccessToken(code);

		HashMap<String, Object> userInfo = kakaoLoginService.getUserInfo(access_Token);
		System.out.println("###nickName###" + userInfo.get("nickName"));
		System.out.println("###email###" + userInfo.get("email"));

		String userEmail = (String) userInfo.get("email");
		String nickName = (String) userInfo.get("nickName");
		int result = userservice.emailCheck(userEmail);

		if (result != 0) {
			kakaoLoginService.setAuthentication(userEmail);
			session.setAttribute("token", access_Token);
			url = "redirect:/shard/";
		} else {
			rttr.addAttribute("userEmail", userEmail);
			rttr.addAttribute("nickName", nickName);
			url = "redirect:/shard/kakaoJoin";
		}
		return url;
	}
	
	@GetMapping("/notice")
	public String notice(@RequestParam int pageNum, Model model) {
		PageVO vo = new PageVO(pageNum, adminService.noticeCount());
		model.addAttribute("list", adminService.noticeList(vo));
		model.addAttribute("page", vo);
		return "notice";
	}
	
	@GetMapping("/noticeGet")
	public String noticeGet(@RequestParam int pageNum, @RequestParam int noticeNum, Model model) {
		model.addAttribute("notice", adminService.getNotice(noticeNum));
		model.addAttribute("pageNum", pageNum);
		return "noticeGet";
	}
	
//	// 카카오 로그인을 할 때
//	@GetMapping("/login/naver")
//	public String naverOauth(@RequestParam(required = false) String code, @RequestParam String state, Model model, RedirectAttributes rttr, HttpSession session)
//			throws Throwable {
//		String url = "";
//		
//		OAuth2AccessToken oauthToken;
//		
//		if (result != 0) {
//			kakaoLoginService.setAuthentication(userEmail);
//			session.setAttribute("token", access_Token);
//			url = "redirect:/shard/";
//		} else {
//			rttr.addAttribute("userEmail", userEmail);
//			rttr.addAttribute("nickName", nickName);
//			url = "redirect:/shard/kakaoJoin";
//		}
//		return url;
//	}

	@PostMapping("/join")
	public String join(ShardMemberVO vo, RedirectAttributes rttr, @RequestParam("birthYear") int year,
			@RequestParam("birthMonth") int month, @RequestParam("birthDay") int day, DeliverAddrVO addrVO) {
		Timestamp dob = Timestamp.valueOf(year + "-" + month + "-" + day+" 0000:00:00");
		vo.setDob(dob);
		vo.setUserPwd(passwordEncoder.encode(vo.getUserPwd()));
		int result = userservice.insertUser(vo);
		userservice.insertAddr(addrVO);
		List<Integer> coupon = Arrays.asList(1,2,3);
		userservice.insertCoupon(coupon, vo.getEmail());
		if (result == 1) {
			rttr.addFlashAttribute("result", "success");
		} else {
			rttr.addFlashAttribute("result", "faild");
		}
		return "redirect:/shard/login";
	}

	@PostMapping("/kakaoJoin")
	public String kakaoJoin(RedirectAttributes rttr, ShardMemberVO vo, @RequestParam("birthYear") int year,
			@RequestParam("birthMonth") int month, @RequestParam("birthDay") int day, DeliverAddrVO addrVO) {
		Timestamp dob = Timestamp.valueOf(year + "-" + month + "-" + day+" 0000:00:00");
		vo.setDob(dob);
		int result = userservice.insertKakaoUser(vo);
		userservice.insertAddr(addrVO);
		if (result == 1) {
			rttr.addFlashAttribute("result", "success");
		} else {
			rttr.addFlashAttribute("result", "faild");
		}
		return "redirect:/shard/login";
	}

}