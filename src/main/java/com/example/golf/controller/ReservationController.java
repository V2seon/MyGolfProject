package com.example.golf.controller;

import com.example.golf.common.Pagination;
import com.example.golf.common.SessionCheck;
import com.example.golf.dto.ReservationSteteDto;
import com.example.golf.entity.CountryClubEntity;
import com.example.golf.entity.CourseEntity;
import com.example.golf.entity.ViewReservationInfoEntity;
import com.example.golf.entity.ViewReservationStateInfoEntity;
import com.example.golf.repository.*;
import com.example.golf.service.ReservationInfoService;
import com.example.golf.service.ReservationStateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@AllArgsConstructor
@RequestMapping
public class ReservationController {

    private ReservationInfoService reservationInfoService;
    private ReservationStateService reservationStateService;
    private CountryClubRepository countryClubRepository;
    private ViewReservationInfoRepository viewReservationInfoRepository;
    private ViewReservationStateInfoRepository viewReservationStateInfoRepository;
    private CourseRepository courseRepository;
    private ReservationStateRepository reservationStateRepository;

    @GetMapping("/Reservation")
    public String Reservation(Model model, HttpServletRequest request, Pageable pageable,
                        @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<ViewReservationInfoEntity> s1 = reservationInfoService.selectALLTable0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Reservation");

            returnValue = "/Reservation/WaitInfoList";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/search_Reservation", method = RequestMethod.POST)
    public String search_Reservation(Model model, HttpServletRequest request,
                        @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                        @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                        @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = reservationInfoService.seALLTable(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<ViewReservationInfoEntity> pageList = reservationInfoService.seALLTable(selectKey, titleText, pageable);

        model.addAttribute("userlist", pageList); //페이지 객체 리스트
        model.addAttribute("nowurl0","/Reservation");

        return "/Reservation/WaitInfoList :: #intable";
    }

    @GetMapping("/Reservation1")
    public String Reservation1(Model model, HttpServletRequest request, Pageable pageable,
                        @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<ViewReservationStateInfoEntity> s1 = reservationStateService.selectALLTable0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현
            LocalDate nowDate = LocalDate.now();
            for(int j=0; j<s1.getContent().size(); j++){
                LocalDate startDate = LocalDate.parse(s1.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
                LocalDateTime date1 = startDate.atStartOfDay();
                LocalDateTime date2 = nowDate.atStartOfDay();
                Long betweenDays = (Long) Duration.between(date2,date1).toDays();
                s1.getContent().get(j).setRsiuino(betweenDays);
            }

            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Reservation");

            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
            List<ViewReservationStateInfoEntity> 어등 = viewReservationStateInfoRepository.findByRsiccno0("어등산");
            List<ViewReservationStateInfoEntity> 해피니스 = viewReservationStateInfoRepository.findByRsiccno0("해피니스");
            List<ViewReservationStateInfoEntity> 무안 = viewReservationStateInfoRepository.findByRsiccno0("무안컨트리클럽");
            List<ViewReservationStateInfoEntity> 아크로 = viewReservationStateInfoRepository.findByRsiccno0("아크로컨트리클럽");
            List<ViewReservationStateInfoEntity> 클린밸리 = viewReservationStateInfoRepository.findByRsiccno0("무안클린밸리");
            List<ViewReservationStateInfoEntity> 광주 = viewReservationStateInfoRepository.findByRsiccno0("광주컨트리클럽");

            for(int i=0; i<s2.size(); i++){
                if(s2.get(i).getCcname().equals("어등산")){
                    s2.get(i).setCctype(어등.size());
                }else if(s2.get(i).getCcname().equals("무안컨트리클럽")){
                    s2.get(i).setCctype(무안.size());
                }else if(s2.get(i).getCcname().equals("해피니스")){
                    s2.get(i).setCctype(해피니스.size());
                }else if(s2.get(i).getCcname().equals("아크로컨트리클럽")){
                    s2.get(i).setCctype(아크로.size());
                }else if(s2.get(i).getCcname().equals("무안클린밸리")){
                    s2.get(i).setCctype(클린밸리.size());
                }else if(s2.get(i).getCcname().equals("광주컨트리클럽")){
                    s2.get(i).setCctype(광주.size());
                }
            }

            model.addAttribute("country",s2);

            returnValue = "/Reservation/NotDefInfoList";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/search_Reservation1", method = RequestMethod.POST)
    public String search_Reservation1(Model model, HttpServletRequest request,
                        @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                        @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                        @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = reservationStateService.seALLTable(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<ViewReservationStateInfoEntity> pageList = reservationStateService.seALLTable(selectKey, titleText, pageable);
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현

        for(int j=0; j<pageList.getContent().size(); j++){
            LocalDate startDate = LocalDate.parse(pageList.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
            LocalDateTime date1 = startDate.atStartOfDay();
            LocalDateTime date2 = nowDate.atStartOfDay();
            Long betweenDays = (Long) Duration.between(date2,date1).toDays();
            pageList.getContent().get(j).setRsiuino(betweenDays);
        }

        model.addAttribute("userlist", pageList); //페이지 객체 리스트
        model.addAttribute("nowurl0","/Reservation");

        return "/Reservation/NotDefInfoList :: #intable";
    }

    @GetMapping("/Reservation3")
    public String Reservation3(Model model, HttpServletRequest request, Pageable pageable,
                        @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<ViewReservationStateInfoEntity> s1 = reservationStateService.selectALLTable1(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Reservation");

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현
            LocalDate nowDate = LocalDate.now();
            for(int j=0; j<s1.getContent().size(); j++){
                LocalDate startDate = LocalDate.parse(s1.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
                LocalDateTime date1 = startDate.atStartOfDay();
                LocalDateTime date2 = nowDate.atStartOfDay();
                Long betweenDays = (Long) Duration.between(date2,date1).toDays();
                s1.getContent().get(j).setRsiuino(betweenDays);
            }

            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
            List<ViewReservationStateInfoEntity> 어등 = viewReservationStateInfoRepository.findByRsiccno1("어등산");
            List<ViewReservationStateInfoEntity> 해피니스 = viewReservationStateInfoRepository.findByRsiccno1("해피니스");
            List<ViewReservationStateInfoEntity> 무안 = viewReservationStateInfoRepository.findByRsiccno1("무안컨트리클럽");
            List<ViewReservationStateInfoEntity> 아크로 = viewReservationStateInfoRepository.findByRsiccno1("아크로컨트리클럽");
            List<ViewReservationStateInfoEntity> 클린밸리 = viewReservationStateInfoRepository.findByRsiccno1("무안클린밸리");
            List<ViewReservationStateInfoEntity> 광주 = viewReservationStateInfoRepository.findByRsiccno1("광주컨트리클럽");

            for(int i=0; i<s2.size(); i++){
                if(s2.get(i).getCcname().equals("어등산")){
                    s2.get(i).setCctype(어등.size());
                }else if(s2.get(i).getCcname().equals("무안컨트리클럽")){
                    s2.get(i).setCctype(무안.size());
                }else if(s2.get(i).getCcname().equals("해피니스")){
                    s2.get(i).setCctype(해피니스.size());
                }else if(s2.get(i).getCcname().equals("아크로컨트리클럽")){
                    s2.get(i).setCctype(아크로.size());
                }else if(s2.get(i).getCcname().equals("무안클린밸리")){
                    s2.get(i).setCctype(클린밸리.size());
                }else if(s2.get(i).getCcname().equals("광주컨트리클럽")){
                    s2.get(i).setCctype(광주.size());
                }
            }
            model.addAttribute("country",s2);

            returnValue = "/Reservation/DefInfoList";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/search_Reservation3", method = RequestMethod.POST)
    public String search_Reservation3(Model model, HttpServletRequest request,
                        @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                        @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                        @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = reservationStateService.seALLTable1(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<ViewReservationStateInfoEntity> pageList = reservationStateService.seALLTable1(selectKey, titleText, pageable);

        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현

        for(int j=0; j<pageList.getContent().size(); j++){
            LocalDate startDate = LocalDate.parse(pageList.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
            LocalDateTime date1 = startDate.atStartOfDay();
            LocalDateTime date2 = nowDate.atStartOfDay();
            Long betweenDays = (Long) Duration.between(date2,date1).toDays();
            pageList.getContent().get(j).setRsiuino(betweenDays);
        }

        model.addAttribute("userlist", pageList); //페이지 객체 리스트
        model.addAttribute("nowurl0","/Reservation");

        return "/Reservation/DefInfoList :: #intable";
    }

    @GetMapping("/RegisterInfo")
    public String RegisterInfo(Model model, HttpServletRequest request, Pageable pageable){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
            List<CourseEntity> s1 = courseRepository.findAll();
            model.addAttribute("country",s2);
            model.addAttribute("course",s1);
            model.addAttribute("nowurl0","/Reservation");
            returnValue = "/Reservation/RegisterInfo";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/SaveInfo")
    public String SaveInfo(HttpServletRequest request, Model model,
                           @RequestParam(required = false, defaultValue = "", value = "cname") Long cname,
                           @RequestParam(required = false, defaultValue = "", value = "id") String id,
                           @RequestParam(required = false, defaultValue = "", value = "date") String date,
                           @RequestParam(required = false, defaultValue = "", value = "course") int course,
                           @RequestParam(required = false, defaultValue = "", value = "cancel") String cancel){
        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ReservationSteteDto reservationSteteDto = new ReservationSteteDto(null,0L,11L,9L,cname,id,date,course,0,sdf1,cancel,0);
        reservationInfoService.StateSave(reservationSteteDto);
        return "redirect:";
    }

    @PostMapping("/clearrs")
    public String clearrs(HttpServletRequest request, Model model,
                            @RequestParam(required = false, defaultValue = "", value = "seq") int seq){
        reservationStateService.changeState(seq);
        return "redirect:";
    }

    @PostMapping("/Delstate")
    public String Delstate(HttpServletRequest request, Model model,
                          @RequestParam(required = false, defaultValue = "", value = "seq") Long seq){
        viewReservationStateInfoRepository.deleteById(seq);
        return "redirect:";
    }

    @PostMapping("/bandup")
    public String bandup(HttpServletRequest request, Model model,
                            @RequestParam(required = false, defaultValue = "", value = "seq") Long seq){
        HttpSession session = request.getSession();
        String ipmsg = String.valueOf(seq);
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

                    arg1 = "C:/Users/eb/주피터/bandset2.py";
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
        return "redirect::";
    }



}
