package org.oobootcamp;
import org.junit.jupiter.api.Test;
import org.oobootcamp.message.GettingResultMessage;
import org.oobootcamp.message.ParkingResultMessage;

import static org.assertj.core.api.Assertions.assertThat;

public class OOBootcampTest {
    @Test
    void should_welcome_to_oo_bootcamp() {
        OOBootcamp ooBootcamp = new OOBootcamp("Hello, Welcome to OOBootcamp");
        assertThat(ooBootcamp.message).isEqualTo("Hello, Welcome to OOBootcamp");
    }

    // 当普通用户停车且停车场有空余车位时，可以停车
    @Test
    void should_parking_given_4_parking_space_and_0_cars_in_park_when_park() {
        //HashMap<String, Integer> CarSpaceInfo spaceInfos;
        ParkingLotMgr parkingLotMar = new ParkingLotMgr(4);
        var parkingResult = parkingLotMar.Park("陕A 11000");
        assertThat(parkingResult.ticketEntity.carPlate).isEqualTo("陕A 11000");
        assertThat(parkingResult.ticketEntity.parkingNumber).isEqualTo(1);
    }
    //当普通用户停车且停车场无空余车位时，不可以停车
    @Test
    void should_not_parking_given_4_parking_space_and_4_cars_in_park_when_park() {
        //HashMap<String, Integer> CarSpaceInfo spaceInfos;
        ParkingLotMgr parkingLotMar = new ParkingLotMgr(4);
        parkingLotMar.Park("陕A 00000");
        parkingLotMar.Park("陕A 10000");
        parkingLotMar.Park("陕A 20000");
        parkingLotMar.Park("陕A 30000");
        var parkingResult = parkingLotMar.Park("陕A 40000");
        assertThat(parkingResult.result).isEqualTo(ParkingResultMessage.FULL.getMessage());
    }
    // 当普通用户取车且持有有效的票时，可以取车
    @Test
    void should_get_car_given_valid_ticket_when_get_car() {
        //HashMap<String, Integer> CarSpaceInfo spaceInfos;
        ParkingLotMgr parkingLotMar = new ParkingLotMgr(4);
        parkingLotMar.Park("陕A 00000");
        parkingLotMar.Park("陕A 10000");
        var ticketEntity = new TicketEntity("陕A 10000", 1);
        assertThat(parkingLotMar.parkingCarsNum).isEqualTo(2);
        var pickResult = parkingLotMar.GetCar(ticketEntity);
        assertThat(pickResult).isEqualTo(ParkingResultMessage.SUCCESS.getMessage());
        assertThat(parkingLotMar.parkingCarsNum).isEqualTo(1);
    }
    // 当普通用户取车且无票时, 无法取车
    @Test
    void should_not_get_car_given_no_ticket_when_get_car() {
        //HashMap<String, Integer> CarSpaceInfo spaceInfos;
        ParkingLotMgr parkingLotMar = new ParkingLotMgr(4);
        parkingLotMar.Park("陕A 00000");
        parkingLotMar.Park("陕A 10000");
        assertThat(parkingLotMar.parkingCarsNum).isEqualTo(2);
        var pickResult = parkingLotMar.GetCar(null);
        assertThat(pickResult).isEqualTo(GettingResultMessage.NO_TICKET_NO_CAR.getMessage());
        assertThat(parkingLotMar.parkingCarsNum).isEqualTo(2);
    }
    // 当普通用户取车且持有无效的票时, 无法取车
    @Test
    void should_not_get_car_given_invalid_ticket_when_get_car() {
        //HashMap<String, Integer> CarSpaceInfo spaceInfos;
        ParkingLotMgr parkingLotMar = new ParkingLotMgr(4);
        parkingLotMar.Park("陕A 00000");
        parkingLotMar.Park("陕A 10000");
        var ticketEntity = new TicketEntity("陕A 50000", 1);
        assertThat(parkingLotMar.parkingCarsNum).isEqualTo(2);
        var pickResult = parkingLotMar.GetCar(ticketEntity);
        assertThat(pickResult).isEqualTo(GettingResultMessage.INVALID_TICKET.getMessage());
        assertThat(parkingLotMar.parkingCarsNum).isEqualTo(2);
    }
}
