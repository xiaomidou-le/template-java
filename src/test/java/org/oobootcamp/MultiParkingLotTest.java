package org.oobootcamp;

import org.junit.jupiter.api.Test;
import org.oobootcamp.message.GettingResultMessage;
import org.oobootcamp.message.ParkingResultMessage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class MultiParkingLotTest {
    // 多个停车场，都没有车，停入第一个停车场
    @Test
    void should_park_into_the_first_given_3_parking_lots_and_no_cars_when_parking(){
        var firstParkingLot = new ParkingLotMgr(4);
        var secondParkingLot = new ParkingLotMgr(3);
        var thirdParkingLot = new ParkingLotMgr(2);
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingLotList.add(thirdParkingLot);
        var parkingLotDispatch = new ParkingLotDispatch(parkingLotList);
        var parkingResult = parkingLotDispatch.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
        assertThat(firstParkingLot.parkingCarsNum).isEqualTo(1);
    }
    // 顺序停车，每一个停车场没停满，停入第一个停车场
    @Test
    void should_park_into_the_first_given_3_parking_lots_and_first_parking_lot_is_not_full_when_parking(){
        var firstParkingLot = new ParkingLotMgr(4);
        var secondParkingLot = new ParkingLotMgr(3);
        var thirdParkingLot = new ParkingLotMgr(2);
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        firstParkingLot.Park("陕A 00000");
        firstParkingLot.Park("陕A 10000");
        firstParkingLot.Park("陕A 20000");
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingLotList.add(thirdParkingLot);
        var parkingLotDispatch = new ParkingLotDispatch(parkingLotList);
        assertThat(firstParkingLot.parkingCarsNum).isLessThan(4);
        var parkingResult = parkingLotDispatch.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
        assertThat(firstParkingLot.parkingCarsNum).isEqualTo(4);
    }

    // 顺序停车，第一个停车场停满，停入第二个停车场
    @Test
    void should_park_into_the_second_given_3_parking_lots_and_first_parking_lot_is_full_when_parking(){
        var firstParkingLot = new ParkingLotMgr(4);
        var secondParkingLot = new ParkingLotMgr(3);
        var thirdParkingLot = new ParkingLotMgr(2);
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        firstParkingLot.Park("陕A 00000");
        firstParkingLot.Park("陕A 10000");
        firstParkingLot.Park("陕A 20000");
        firstParkingLot.Park("陕A 30000");
        secondParkingLot.Park("陕U 32000");
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingLotList.add(thirdParkingLot);
        var parkingLotDispatch = new ParkingLotDispatch(parkingLotList);
        assertThat(secondParkingLot.parkingCarsNum).isLessThan(3);
        var parkingResult = parkingLotDispatch.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
        assertThat(secondParkingLot.parkingCarsNum).isEqualTo(2);
    }

    // 顺序停车，第一个第二个停车场停满，停入第三个个停车场
    @Test
    void should_park_into_the_second_given_3_parking_lots_and_first_and_second_parking_lot_is_full_when_parking(){
        var firstParkingLot = new ParkingLotMgr(4);
        var secondParkingLot = new ParkingLotMgr(3);
        var thirdParkingLot = new ParkingLotMgr(2);
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        firstParkingLot.Park("陕A 00000");
        firstParkingLot.Park("陕A 10000");
        firstParkingLot.Park("陕A 20000");
        firstParkingLot.Park("陕A 30000");
        secondParkingLot.Park("陕U 32000");
        secondParkingLot.Park("陕U 33000");
        secondParkingLot.Park("陕U 34000");
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingLotList.add(thirdParkingLot);
        var parkingLotDispatch = new ParkingLotDispatch(parkingLotList);
        assertThat(thirdParkingLot.parkingCarsNum).isLessThan(2);
        var parkingResult = parkingLotDispatch.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
        assertThat(thirdParkingLot.parkingCarsNum).isEqualTo(1);
    }

    // 所有的停车场停满，不能停车
    @Test
    void should_not_park_into_given_all_parking_lots_is_full_when_parking(){
        var firstParkingLot = new ParkingLotMgr(4);
        var secondParkingLot = new ParkingLotMgr(3);
        var thirdParkingLot = new ParkingLotMgr(2);
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        firstParkingLot.Park("陕A 00000");
        firstParkingLot.Park("陕A 10000");
        firstParkingLot.Park("陕A 20000");
        firstParkingLot.Park("陕A 30000");
        parkingLotList.add(firstParkingLot);

        secondParkingLot.Park("陕A 20000");
        secondParkingLot.Park("陕A 21000");
        secondParkingLot.Park("陕A 22000");
        parkingLotList.add(secondParkingLot);

        thirdParkingLot.Park("陕A 30000");
        thirdParkingLot.Park("陕A 31000");
        parkingLotList.add(thirdParkingLot);
        var parkingLotDispatch = new ParkingLotDispatch(parkingLotList);
        var parkingResult = parkingLotDispatch.Park("陕U 55B28");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.FULL.getMessage());
    }
    // 只要停车场里有我的车，我就可以凭停车券取出来
    @Test
    void should_get_car_given_the_car_is_in_first_parking_lot_when_getting(){
        var firstParkingLot = new ParkingLotMgr(4);
        var secondParkingLot = new ParkingLotMgr(3);
        var thirdParkingLot = new ParkingLotMgr(2);
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        firstParkingLot.Park("陕A 00000");
        firstParkingLot.Park("陕A 11122");
        firstParkingLot.Park("陕A 20000");
        firstParkingLot.Park("陕A 30000");
        parkingLotList.add(firstParkingLot);

        secondParkingLot.Park("陕A 20000");
        secondParkingLot.Park("陕A 21000");
        secondParkingLot.Park("陕A 22000");
        parkingLotList.add(secondParkingLot);

        thirdParkingLot.Park("陕A 30000");
        thirdParkingLot.Park("陕A 31000");
        parkingLotList.add(thirdParkingLot);
        var parkingLotDispatch = new ParkingLotDispatch(parkingLotList);
        TicketEntity ticketEntity = new TicketEntity("陕A 11122", 2);
        assertThat(firstParkingLot.parkingCarsNum).isEqualTo(4);
        var gettingCarResult = parkingLotDispatch.GetCar(ticketEntity);
        assertThat(firstParkingLot.parkingCarsNum).isEqualTo(3);
        assertThat(gettingCarResult).isEqualTo(GettingResultMessage.SUCCESS.getMessage());
    }

    @Test
    void should_get_car_given_the_car_is_in_second_parking_lot_when_getting(){
        var firstParkingLot = new ParkingLotMgr(4);
        var secondParkingLot = new ParkingLotMgr(3);
        var thirdParkingLot = new ParkingLotMgr(2);
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        firstParkingLot.Park("陕A 00000");
        firstParkingLot.Park("陕A 11122");
        firstParkingLot.Park("陕A 20000");
        firstParkingLot.Park("陕A 30000");
        parkingLotList.add(firstParkingLot);

        secondParkingLot.Park("陕A 20000");
        secondParkingLot.Park("陕A 21333");
        secondParkingLot.Park("陕A 22000");
        parkingLotList.add(secondParkingLot);

        thirdParkingLot.Park("陕A 30000");
        thirdParkingLot.Park("陕A 31000");
        parkingLotList.add(thirdParkingLot);
        var parkingLotDispatch = new ParkingLotDispatch(parkingLotList);
        TicketEntity ticketEntity = new TicketEntity("陕A 21333", 2);
        assertThat(secondParkingLot.parkingCarsNum).isEqualTo(3);
        var gettingCarResult = parkingLotDispatch.GetCar(ticketEntity);
        assertThat(gettingCarResult).isEqualTo(GettingResultMessage.SUCCESS.getMessage());
        assertThat(secondParkingLot.parkingCarsNum).isEqualTo(2);
    }
    // 停车场里没有我的车，我无论如何都无法取出车
    @Test
    void should_not_get_car_given_no_car_is_in_second_parking_lot_when_getting(){
        var firstParkingLot = new ParkingLotMgr(4);
        var secondParkingLot = new ParkingLotMgr(3);
        var thirdParkingLot = new ParkingLotMgr(2);
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        firstParkingLot.Park("陕A 00000");
        firstParkingLot.Park("陕A 11122");
        firstParkingLot.Park("陕A 20000");
        firstParkingLot.Park("陕A 30000");
        parkingLotList.add(firstParkingLot);

        secondParkingLot.Park("陕A 20000");
        secondParkingLot.Park("陕A 21333");
        secondParkingLot.Park("陕A 22000");
        parkingLotList.add(secondParkingLot);

        thirdParkingLot.Park("陕A 30000");
        thirdParkingLot.Park("陕A 31000");
        parkingLotList.add(thirdParkingLot);
        var parkingLotDispatch = new ParkingLotDispatch(parkingLotList);
        TicketEntity ticketEntity = new TicketEntity("陕A 55555", 2);
        var gettingCarResult = parkingLotDispatch.GetCar(ticketEntity);
        assertThat(gettingCarResult).isEqualTo(GettingResultMessage.INVALID_TICKET.getMessage());
    }
    // （停车场停车后，1，2号停满，3号为停满，从1号停车场取车1辆，再来停车，需要停在1号停车场）
    @Test
    void should_parking_car_in_first_parking_log_given_the_first_parking_lot_have_spaces_and_the_second_and_third_has_when_getting(){
        var firstParkingLot = new ParkingLotMgr(4);
        var secondParkingLot = new ParkingLotMgr(3);
        var thirdParkingLot = new ParkingLotMgr(2);
        List<ParkingLotMgr> parkingLotList = new ArrayList<>();;
        firstParkingLot.Park("陕A 00000");
        firstParkingLot.Park("陕A 11122");
        firstParkingLot.Park("陕A 20000");
        firstParkingLot.Park("陕A 30000");
        parkingLotList.add(firstParkingLot);

        secondParkingLot.Park("陕A 20000");
        secondParkingLot.Park("陕A 21333");
        secondParkingLot.Park("陕A 22000");
        parkingLotList.add(secondParkingLot);

        thirdParkingLot.Park("陕A 30000");
        parkingLotList.add(thirdParkingLot);
        var parkingLotDispatch = new ParkingLotDispatch(parkingLotList);
        TicketEntity ticketEntity = new TicketEntity("陕A 11122", 2);
        assertThat(firstParkingLot.parkingCarsNum).isEqualTo(4);
        var gettingCarResult = parkingLotDispatch.GetCar(ticketEntity);
        assertThat(gettingCarResult).isEqualTo(GettingResultMessage.SUCCESS.getMessage());
        assertThat(firstParkingLot.parkingCarsNum).isEqualTo(3);

        var parkingCarResult = parkingLotDispatch.Park("陕B 00000");
        assertThat(firstParkingLot.parkingCarsNum).isEqualTo(4);
        assertThat(parkingCarResult.result).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
    }
}
