package com.example.golf.controller;

import com.example.golf.common.SessionCheck;
import com.example.golf.dto.ReservationInfoDto;
import com.example.golf.entity.*;
import com.example.golf.repository.*;
import com.example.golf.service.CountryclubService;
import com.example.golf.service.ReservationInfoService;
import com.example.golf.service.ReservationStateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainController {

    private CountryAccountRepository countryAccountRepository;
    private RlRepository rlRepository;
    private UserinfoRepository userinfoRepository;
    private ReservationInfoService reservationInfoService;
    private ReservationInfoRepository reservationInfoRepository;
    private ReservationStateRepository reservationStateRepository;
    private ReservationStateService reservationStateService;
    private CountryClubRepository countryClubRepository;
    private CountryclubService countryclubService;

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/Main")
    public String main(Model m, HttpServletRequest request){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            returnValue = "GolfMain";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/gjoin")
    public String gjoin(){
        return "golfjoin";
    }

    @GetMapping("/greservationi")
    public String greservationi(){
        return "golfclupinsert";
    }

    @GetMapping("/greservations")
    public String greservations(Model m, HttpServletRequest request) {
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            String sessioninid = (String) session.getAttribute("user_signature");
            Optional<UserinfoEntity> guet = userinfoRepository.findByUiid(sessioninid);
            Long finduinum = guet.get().getUino();
            List<ReservationInfoEntity> listgolfentity = reservationInfoRepository.findAll5(finduinum);
            m.addAttribute("Rvinfo",listgolfentity);
            returnValue = "formRV";
        }else{
            returnValue = "formLI";
        }
        return returnValue;
    }

    @GetMapping("/formRI")
    public String formRI(Model model, HttpServletRequest request, Pageable pageable,
                              @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        String returnValue = "";
        if (new SessionCheck().loginSessionCheck(request)) {
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            List<CountryClubEntity> s1 = countryClubRepository.findAll();

            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/formRI");
            returnValue = "/formRI/formRI1";
        } else {
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/formRIgo")
    public String formRIgo(Model model, HttpServletRequest request,
                           @RequestParam(required = false ,defaultValue = "" , value="seq") Long seq){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            session.setAttribute("seq",seq);
            model.addAttribute("nowurl0","/formRI");
            returnValue = "redirect:";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/formRI17")
    public String formRI17(Model model, HttpServletRequest request){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            model.addAttribute("nowurl0","/formRI");
            returnValue = "/formRI/formRI17";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/formRI40")
    public String formRI40(Model model, HttpServletRequest request){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            model.addAttribute("nowurl0","/formRI");
            returnValue = "/formRI/formRI40";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/golftest2")
    public String golftest2(HttpServletRequest request, Model model,
                            @RequestParam(required = false, defaultValue = "", value = "uid") String uid,
                            @RequestParam(required = false, defaultValue = "", value = "upw") String upw, String... args){
        String ipmsg = uid + "/" + upw;
        System.out.println(ipmsg);
        // 클라이언트 소켓을 받을 threadpool를 선언한다. 쓰레드 풀안에는 최대 10개의 쓰레드를 가동시킬 수 있다.
        ExecutorService clientService = Executors.newFixedThreadPool(10);
        // serverSocket를 선언한다.
        try (ServerSocket server = new ServerSocket()) {
            System.out.println("소켓서버 오픈");
            // 포트는 9513으로 오픈한다.
            InetSocketAddress ipep = new InetSocketAddress(9513);
            server.bind(ipep);
            clientService.submit(() -> {
                try{
                    String arg1;
                    ProcessBuilder builder;
                    BufferedReader br;

                    arg1 = "C:/Users/abcd/Desktop/selenium/selenium/golftest4.py";
                    System.out.println("여긴 안오는거니?");
                    builder = new ProcessBuilder("python", arg1);

                    builder.redirectErrorStream(true);
                    Process process = builder.start();

                    int exitval = process.waitFor();

                    br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

                    String line;
                    while ((line = br.readLine()) != null){
                        System.out.println(">>> " + line);
                    }

                    if(exitval != 0){
                        System.out.println("비정상종료");
                    }
                } catch (Throwable e){
                    e.printStackTrace();
                }
            });

            while (true) {
                System.out.println("와일문 안이잖아");
                // 클라이언트가 접속할 때까지 대기한다.
                Socket client = server.accept();
                System.out.println("1");
                // 클라이언트가 접속이 되면 쓰레드 풀에 쓰레드를 하나 생성하고 inputstream과 outputstream을 받는다.
                clientService.submit(() -> {
                    try (OutputStream sender = client.getOutputStream();
                         InputStream receiver = client.getInputStream();) {
                        System.out.println("2");
                        // 서버 무한 대기를 한다.
                        while (true) {
                            System.out.println("와일문 안이잖아2");
                            byte[] data = new byte[4];
                            // 데이터 길이를 받는다.
                            receiver.read(data, 0, 4);
                            // ByteBuffer를 통해 little 엔디언 형식으로 데이터 길이를 구한다.
                            ByteBuffer b = ByteBuffer.wrap(data);
                            b.order(ByteOrder.LITTLE_ENDIAN);
                            int length = b.getInt();
                            // 데이터를 받을 버퍼를 선언한다.
                            data = new byte[length];
                            // 데이터를 받는다.
                            receiver.read(data, 0, length);

                            // byte형식의 데이터를 string형식으로 변환한다.
                            String msg = new String(data, "UTF-8");
                            // 콘솔에 출력한다.
                            System.out.println(msg);
                            // echo를 붙힌다.
                            msg = ipmsg;
                            // string을 byte배열 형식으로 변환한다.
                            data = msg.getBytes();
                            // ByteBuffer를 통해 데이터 길이를 byte형식으로 변환한다.
                            b = ByteBuffer.allocate(4);
                            // byte포멧은 little 엔디언이다.
                            b.order(ByteOrder.LITTLE_ENDIAN);
                            b.putInt(data.length);
                            // 데이터 길이 전송
                            sender.write(b.array(), 0, 4);
                            // 데이터 전송
                            sender.write(data);
                            server.close();
                            break;
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            // 에러가 발생하면 접속을 종료한다.
                            client.close();
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return "golfchoice";
    }

    @PostMapping("/golftest3")
    public String golftest3(HttpServletRequest request, Model model,
                            @RequestParam(required = false, defaultValue = "", value = "mountin") Long mountin,
                            @RequestParam(required = false, defaultValue = "", value = "startdate") String startdate,
                            @RequestParam(required = false, defaultValue = "", value = "enddate") String enddate,
                            @RequestParam(required = false, defaultValue = "", value = "id") String id,
                            @RequestParam(required = false, defaultValue = "", value = "pw") String pw,
                            @RequestParam(required = false, defaultValue = "", value = "hope_t1") int hope_t1,
                            @RequestParam(required = false, defaultValue = "", value = "hope_t2") int hope_t2,
                            @RequestParam(required = false, defaultValue = "", value = "hope_h") int hope_h,
                            @RequestParam(required = false, defaultValue = "", value = "hope_c") int hope_c,
                            @RequestParam(required = false, defaultValue = "", value = "type") int type){
        System.out.println("11");
        HttpSession session = request.getSession();
        String sessioninid = (String) session.getAttribute("user_signature");
        System.out.println(sessioninid);
        Optional<UserinfoEntity> aaa = userinfoRepository.findByUiid(sessioninid);
        Long uino = aaa.get().getUino();
        System.out.println(uino);
        Optional<CountryAccountEntity> bbb = countryAccountRepository.findByCauinoAndCaccno(uino, mountin);
        Long cano = bbb.get().getCano();
        String caid = bbb.get().getCaid();
        String capw = bbb.get().getCapassword();
        System.out.println(caid);
        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Optional<CountryClubEntity> c1 = countryClubRepository.findByCcno(mountin);
        String canday = String.valueOf(c1.get().getCccancelday());
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(null, cano, uino, mountin, caid, capw, startdate, enddate, hope_t1, hope_t2, hope_h, hope_c, 0, 0, 0,0, null, type, canday,null, sdf1, null);
        reservationInfoService.insertData1(reservationInfoDto);
        String ipmsg = cano+"/"+uino+"/"+uino;
        System.out.println(ipmsg);

        // 클라이언트 소켓을 받을 threadpool를 선언한다. 쓰레드 풀안에는 최대 10개의 쓰레드를 가동시킬 수 있다.
        ExecutorService clientService = Executors.newFixedThreadPool(10);
        // serverSocket를 선언한다.
        try (ServerSocket server = new ServerSocket()) {
            System.out.println("소켓서버 오픈");
            // 포트는 9514으로 오픈한다.
            InetSocketAddress ipep = new InetSocketAddress(9514);
            server.bind(ipep);
            clientService.submit(() -> {
                try{
                    String arg1;
                    ProcessBuilder builder;
                    BufferedReader br;

                    arg1 = "C:/Users/abcd/Desktop/selenium/selenium/golftest4.py";
                    System.out.println("여긴 안오는거니?");
                    builder = new ProcessBuilder("python", arg1);

                    builder.redirectErrorStream(true);
                    Process process = builder.start();

                    int exitval = process.waitFor();

                    br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

                    String line;
                    while ((line = br.readLine()) != null){
                        System.out.println(">>> " + line);
                    }

                    if(exitval != 0){
                        System.out.println("비정상종료");
                    }
                } catch (Throwable e){
                    e.printStackTrace();
                }
            });

            while (true) {
                System.out.println("와일문 안이잖아");
                // 클라이언트가 접속할 때까지 대기한다.
                Socket client = server.accept();
                System.out.println("1");
                // 클라이언트가 접속이 되면 쓰레드 풀에 쓰레드를 하나 생성하고 inputstream과 outputstream을 받는다.
                clientService.submit(() -> {
                    try (OutputStream sender = client.getOutputStream();
                         InputStream receiver = client.getInputStream();) {
                        System.out.println("2");
                        // 서버 무한 대기를 한다.
                        while (true) {
                            System.out.println("와일문 안이잖아2");
                            byte[] data = new byte[4];
                            // 데이터 길이를 받는다.
                            receiver.read(data, 0, 4);
                            // ByteBuffer를 통해 little 엔디언 형식으로 데이터 길이를 구한다.
                            ByteBuffer b = ByteBuffer.wrap(data);
                            b.order(ByteOrder.LITTLE_ENDIAN);
                            int length = b.getInt();
                            // 데이터를 받을 버퍼를 선언한다.
                            data = new byte[length];
                            // 데이터를 받는다.
                            receiver.read(data, 0, length);

                            // byte형식의 데이터를 string형식으로 변환한다.
                            String msg = new String(data, "UTF-8");
                            // 콘솔에 출력한다.
                            System.out.println(msg);
                            // echo를 붙힌다.
                            msg = ipmsg;
                            // string을 byte배열 형식으로 변환한다.
                            data = msg.getBytes();
                            // ByteBuffer를 통해 데이터 길이를 byte형식으로 변환한다.
                            b = ByteBuffer.allocate(4);
                            // byte포멧은 little 엔디언이다.
                            b.order(ByteOrder.LITTLE_ENDIAN);
                            b.putInt(data.length);
                            // 데이터 길이 전송
                            sender.write(b.array(), 0, 4);
                            // 데이터 전송
                            sender.write(data);
                            server.close();
                            break;
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            // 에러가 발생하면 접속을 종료한다.
                            client.close();
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "/formRI/formRI1";
    }


}
