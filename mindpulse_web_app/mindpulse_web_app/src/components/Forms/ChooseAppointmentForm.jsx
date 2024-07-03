import React, {useEffect, useState} from 'react';
import {DatePicker, Space, Flex, Radio, Col, Row, Button} from "antd";
import dayjs from "dayjs";

const divStyle = {
    margin: '0px 0px 0px 250px'
}

const formDivStyle = {
    margin: '50px 0px 0px 0px',
}

const formLabelSpanStyle = {
    fontSize: '16px',
    fontWeight: 'bold',
}

const wrapperStyle = {
    margin: '10px 0px 0px 0px',
    width: 300,
};

const ChooseAppointmentForm = ({serviceOffered, therapistType, taxRate, offDates, workingDays, workingStartTime, workingEndTime, unavailableSlots}) => {
    const mapTherapistType = (type) => {
        switch (type) {
            case 1:
                return 'ClinicalPsychologist';

            case 2:
                return 'Counsellor';

            case 3:
                return 'Trainee';

            default:
                return '';
        }
    };

    const normalizedTherapistType = mapTherapistType(therapistType);

    const [mode, setMode] = useState("inPerson");
    const [session, setSession] = useState("firstSession");
    const [selectedService, setSelectedService] = useState(serviceOffered[0]);
    const dayjsOffDates = offDates.map(dateString => dayjs(dateString));
    const [selectedTime, setSelectedTime] = useState(null);
    const [selectedDate, setSelectedDate] = useState(null);

    const S1 = serviceOffered.find(service => service.sk === "S1");
    const S2 = serviceOffered.find(service => service.sk === "S2");

    const S1Name = S1.name;
    const S1Price = mode === 'online' ? S1.rate.OnlineRate[mapTherapistType(therapistType)] : S1.rate.InPersonRate[mapTherapistType(therapistType)];
    const S2Name = S2.name;
    const S2Price = mode === 'online' ? S2.rate.OnlineRate[mapTherapistType(therapistType)] : S2.rate.InPersonRate[mapTherapistType(therapistType)];

    const onChange = (date, dateString) => {
        setSelectedDate(date);
        setSelectedTime(null); // Reset selected time when date changes
    };

    const disabledDates = (current) => {
        if (current && current < dayjs().startOf('day')) {
            return true;
        }
        if (!workingDays.includes(current.day())) {
            return true;
        }
        if (dayjsOffDates.some(date => date.isSame(current, 'day'))) {
            return true;
        }
        return false;
    };

    const calculateTotalPrice = () => {
        let basePrice = session === "firstSession" ? S1Price + S2Price : S2Price;
        let tax = basePrice * taxRate;
        return basePrice + tax;
    };

    const generateTimeSlots = (start, end) => {
        let slots = [];
        let current = dayjs(start, 'HH:mm');
        const endDayjs = dayjs(end, 'HH:mm');

        while (current.isBefore(endDayjs) || current.isSame(endDayjs)) {
            slots.push(current.format('HH:mm'));
            current = current.add(2, 'hour');
        }

        return slots;
    };

    const timeSlots = selectedDate ? generateTimeSlots(workingStartTime, workingEndTime).filter(slot => {
        return !unavailableSlots.some(unavailableSlot => {
            const slotDateTime = selectedDate.format('YYYY-MM-DD') + 'T' + slot + ':00';
            return unavailableSlot === slotDateTime;
        });
    }) : [];

    const handleTimeSelect = (time) => {
        setSelectedTime(prevSelectedTime => prevSelectedTime === time ? null : time);
    };

    return (
        <>
            <div style={divStyle}>
                <Row>
                    <Col span={24}>
                        <span style={{fontSize: '24px', fontWeight: 'bold', margin: '0px 0px 50px 220px'}}>Choose Appointment</span>
                    </Col>
                </Row>

                <div style={formDivStyle}>
                    <Row style={{margin: "0px 0px 75px 0px"}}>
                        <Col span={12}>
                            <div style={{margin: '0px 0px 30px 0px'}}>
                                <span style={formLabelSpanStyle}>Mode of conduct</span>
                                <Flex vertical gap="middle" style={{margin: '10px 0px 0px 0px'}}>
                                    <Radio.Group defaultValue="inPerson" onChange={(e) => setMode(e.target.value)}>
                                        <Radio.Button value="inPerson">In-Person</Radio.Button>
                                        <Radio.Button value="online">Online</Radio.Button>
                                    </Radio.Group>
                                </Flex>
                            </div>

                            <div style={{margin: '0px 0px 35px 0px'}}>
                                <span style={formLabelSpanStyle}>Session</span>
                                <Flex vertical gap="middle" style={{margin: '10px 0px 0px 0px'}}>
                                    <Radio.Group defaultValue="firstSession" onChange={(e) => setSession(e.target.value)}>
                                        <Radio.Button value="firstSession">First session</Radio.Button>
                                        <Radio.Button value="returningSession">Returning session</Radio.Button>
                                    </Radio.Group>
                                </Flex>
                            </div>

                            <div>
                                <Space direction="vertical">
                                    <span style={formLabelSpanStyle}>Select Date</span>
                                    <DatePicker onChange={onChange} disabledDate={disabledDates} placeholder="No date selected" style={{width: '255px'}}/>
                                </Space>
                            </div>
                        </Col>

                        <Col span={12}>
                            <Space direction="vertical" size="small" style={{display: 'flex', height: '170px'}}>
                                <span style={formLabelSpanStyle}>Price</span>
                                <div style={{margin: '0px 0px 51px 30px'}}>
                                    <Space direction="vertical" size="small" style={{display: 'flex'}}>
                                        {session === "firstSession" && (
                                            <Row>
                                                <Col span={12}>
                                                    <span style={{ fontSize: '12px' }}>{S1Name}:</span>
                                                </Col>
                                                <Col span={12}>
                                                    <span style={{ fontSize: '12px' }}>RM {S1Price.toFixed(2)}</span>
                                                </Col>
                                            </Row>
                                        )}
                                        <Row>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>{S2Name}:</span>
                                            </Col>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>RM {S2Price.toFixed(2)}</span>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>Tax ({(taxRate * 100).toFixed(0)}%):</span>
                                            </Col>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>RM {(calculateTotalPrice() - (session === "firstSession" ? S1Price + S2Price : S2Price)).toFixed(2)}</span>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col span={12}>
                                                <span style={{fontSize: '14px', fontWeight:'bold'}}>Total Price:</span>
                                            </Col>
                                            <Col span={12}>
                                                <span style={{fontSize: '14px', fontWeight:'bold'}}>RM {calculateTotalPrice().toFixed(2)}</span>
                                            </Col>
                                        </Row>
                                    </Space>
                                </div>
                            </Space>

                            <Space direction="vertical" size="middle" style={{ display: 'flex', height: '100px' }}>
                                <span style={formLabelSpanStyle}>Select Time</span>
                                <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px' }}>
                                    {timeSlots.map((slot, index) => (
                                        <Button
                                            key={index}
                                            size="small"
                                            type={slot === selectedTime ? 'primary' : 'default'}
                                            onClick={() => handleTimeSelect(slot)}
                                        >
                                            {dayjs(slot, 'HH:mm').format('hh:mm A')}
                                        </Button>
                                    ))}
                                </div>
                            </Space>
                        </Col>
                    </Row>
                </div>
            </div>
        </>
    )
}

export default ChooseAppointmentForm;