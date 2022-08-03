package org.oobootcamp;

import org.junit.jupiter.api.Test;
import org.oobootcamp.message.GettingResultMessage;
import org.oobootcamp.message.ParkingResultMessage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EffectParkingLotTest {
    // A、B均未停满，顺序停入空车位最多的A停车场
    @Test
    void should_park_into_A_given_2_Cars_In_A_and_1_Cars_In_B_when_parking(){
        var parkingLotA = new ParkingLotMgr(4);
        parkingLotA.Park("陕A 10000");
        parkingLotA.Park("陕A 20000");
        var parkingLotB = new ParkingLotMgr(3);
        parkingLotB.Park("陕B 10000");
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var smartParkingLotBuddy = new SmartParkingLotBuddy(parkingLotList);

        var parkingResult = smartParkingLotBuddy.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
        var getCarResult = parkingLotA.GetCar(parkingResult.ticketEntity);
        assertThat(getCarResult).isEqualTo(GettingResultMessage.SUCCESS.getMessage());
    }

    @Test
    void should_park_into_B_given_3_Cars_In_A_and_1_Cars_In_B_when_parking(){
        var parkingLotA = new ParkingLotMgr(4);
        parkingLotA.Park("陕A 10000");
        parkingLotA.Park("陕A 20000");
        parkingLotA.Park("陕A 30000");
        var parkingLotB = new ParkingLotMgr(3);
        parkingLotB.Park("陕B 10000");
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var smartParkingLotBuddy = new SmartParkingLotBuddy(parkingLotList);

        var parkingResult = smartParkingLotBuddy.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
        var getCarResult = parkingLotB.GetCar(parkingResult.ticketEntity);
        assertThat(getCarResult).isEqualTo(GettingResultMessage.SUCCESS.getMessage());
    }

    @Test
    void should_park_into_A_given_1_Cars_In_A_and_1_Cars_In_B_when_parking(){
        var parkingLotA = new ParkingLotMgr(4);
        parkingLotA.Park("陕A 10000");
        var parkingLotB = new ParkingLotMgr(3);
        parkingLotB.Park("陕B 10000");
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var smartParkingLotBuddy = new SmartParkingLotBuddy(parkingLotList);

        var parkingResult = smartParkingLotBuddy.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
        var getCarResult = parkingLotA.GetCar(parkingResult.ticketEntity);
        assertThat(getCarResult).isEqualTo(GettingResultMessage.SUCCESS.getMessage());
    }

    @Test
    void should_park_into_B_given_4_Cars_In_A_and_1_Cars_In_B_when_parking(){
        var parkingLotA = new ParkingLotMgr(4);
        parkingLotA.Park("陕A 10000");
        parkingLotA.Park("陕A 20000");
        parkingLotA.Park("陕A 30000");
        parkingLotA.Park("陕A 40000");
        var parkingLotB = new ParkingLotMgr(3);
        parkingLotB.Park("陕B 10000");
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var smartParkingLotBuddy = new SmartParkingLotBuddy(parkingLotList);

        var parkingResult = smartParkingLotBuddy.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
        var getCarResult = parkingLotB.GetCar(parkingResult.ticketEntity);
        assertThat(getCarResult).isEqualTo(GettingResultMessage.SUCCESS.getMessage());
    }

    //A停满、B停满，停车失败，提示已满
    @Test
    void should_not_park_given_4_Cars_In_A_and_3_Cars_In_B_when_parking(){
        var parkingLotA = new ParkingLotMgr(4);
        parkingLotA.Park("陕A 10000");
        parkingLotA.Park("陕A 20000");
        parkingLotA.Park("陕A 30000");
        parkingLotA.Park("陕A 40000");
        var parkingLotB = new ParkingLotMgr(3);
        parkingLotB.Park("陕B 10000");
        parkingLotB.Park("陕B 20000");
        parkingLotB.Park("陕B 30000");
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var smartParkingLotBuddy = new SmartParkingLotBuddy(parkingLotList);

        var parkingResult = smartParkingLotBuddy.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.FULL.getMessage());
    }

    //小弟凭自己管理的停车场中的有效票取到对应的车
    @Test
    void should_get_car_given_valid_ticket_when_getting_car(){
        var parkingLotA = new ParkingLotMgr(4);
        parkingLotA.Park("陕A 10000");
        parkingLotA.Park("陕A 20000");
        var parkingLotB = new ParkingLotMgr(3);
        parkingLotB.Park("陕B 10000");
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var smartParkingLotBuddy = new SmartParkingLotBuddy(parkingLotList);

        var gettingCarResult = smartParkingLotBuddy.GetCar(new TicketEntity("陕A 10000",1));
        assertThat(gettingCarResult).isEqualTo(GettingResultMessage.SUCCESS.getMessage());
    }

    //小弟凭无效票取车失败，获得提示信息“无效 Ticket”
    @Test
    void should_not_get_car_given_not_valid_ticket_when_getting_car(){
        var parkingLotA = new ParkingLotMgr(4);
        parkingLotA.Park("陕A 10000");
        parkingLotA.Park("陕A 20000");
        var parkingLotB = new ParkingLotMgr(3);
        parkingLotB.Park("陕B 10000");
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var smartParkingLotBuddy = new SmartParkingLotBuddy(parkingLotList);

        var gettingCarResult = smartParkingLotBuddy.GetCar(new TicketEntity("陕U 10000",1));
        assertThat(gettingCarResult).isEqualTo(GettingResultMessage.INVALID_TICKET.getMessage());
    }

    @Test
    void should_not_get_car_given_no_ticket_when_getting_car(){
        var parkingLotA = new ParkingLotMgr(4);
        parkingLotA.Park("陕A 10000");
        parkingLotA.Park("陕A 20000");
        var parkingLotB = new ParkingLotMgr(3);
        parkingLotB.Park("陕B 10000");
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var smartParkingLotBuddy = new SmartParkingLotBuddy(parkingLotList);

        var gettingCarResult = smartParkingLotBuddy.GetCar(null);
        assertThat(gettingCarResult).isEqualTo(GettingResultMessage.INVALID_TICKET.getMessage());
    }
}
