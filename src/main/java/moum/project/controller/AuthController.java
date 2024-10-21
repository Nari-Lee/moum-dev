package moum.project.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import moum.project.service.UserService;
import moum.project.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : moum.project.controller
 * fileName       : AuthController
 * author         : narilee
 * date           : 24. 10. 21.
 * description    : AuthController는 사용자 인증과 관련된 요청을 처리하는 Spring MVC 컨트롤러입니다.
 *                  로그인, 로그아웃 기능을 제공합니다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 10. 21.        narilee       최초 생성
 */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  /**
   * 로그인 폼을 표시합니다.
   */
  @GetMapping("form")
  public void form() {
  }

  /**
   * 사용자 로그인을 처리합니다.
   *
   * @param email 사용자 이메일
   * @param password 사용자 비밀번호
   * @param saveEmail 이메일 저장 여부
   * @param res HTTP 응답 객체
   * @param session HTTP 세션 객체
   * @return 로그인 성공 시 홈페이지로 리다이렉트, 실패 시 실패 페이지 반환
   * @throws Exception 로그인 처리 중 발생할 수 있는 예외
   */
  @PostMapping("login")
  public String login(
      String email,
      String password,
      boolean saveEmail,
      HttpServletResponse res,
      HttpSession session) throws Exception {

    User user = userService.exists(email, password);
    if (user == null) {
      res.setHeader("Refresh", "2; url=form");
      return "auth/fail";
    }

    if (saveEmail) {
      Cookie cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 7);
      res.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "test@test.com");
      cookie.setMaxAge(0);
      res.addCookie(cookie);
    }

    session.setAttribute("loginUser", user);
    return "redirect:/";
  }

  /**
   * 사용자 로그아웃을 처리합니다.
   *
   * @param session HTTP 세션 객체
   * @return 로그아웃 후 홈페이지로 리다이렉트
   */
  @GetMapping("logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
}
