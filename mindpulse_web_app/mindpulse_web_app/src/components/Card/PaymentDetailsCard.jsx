import React from 'react';
import {Col, Divider, Row, Image} from "antd";

const formLabelSpanStyle = {
    fontSize: '16px',
    fontWeight: 'bold',
}

const PaymentDetailsCard = ({totalPrice, accountNumber, accountName, bankName, duitNowQrImageUrl}) => {
    return(
        <>
            <span style={formLabelSpanStyle}>Payment Details</span>
            <Divider style={{margin: '10px 0px 10px 0px'}}/>
            <div>
                <span style={{opacity: '45%'}}>Please pay </span>
                <span style={{opacity: '100%', fontWeight: 'bold'}}>RM {totalPrice.toFixed(2)}</span>
                <span style={{opacity: '45%'}}> to confirm your appointment</span>
            </div>

            <div style={{margin: '20px 0px 0px 0px'}}>
                <span style={{opacity: '45%', fontWeight: 'bold'}}>Bank Transfer</span>
                <div style={{margin: '10px 0px 5px 0px'}}>
                    <Row style={{margin: '0px 0px 20px 15px', opacity: '45%'}}>
                        <Col span={12} style={{margin: '0px 0px 10px 0px'}}>
                            <span>Account Number</span>
                        </Col>

                        <Col span={12} style={{textAlign: 'right', margin: '0px 0px 10px 0px'}}>
                            <span>{accountNumber}</span>
                        </Col>

                        <Col span={12} style={{margin: '0px 0px 10px 0px'}}>
                            <span>Account Name</span>
                        </Col>

                        <Col span={12} style={{textAlign: 'right', margin: '0px 0px 10px 0px'}}>
                            <span>{accountName}</span>
                        </Col>

                        <Col span={12}>
                            <span>Bank</span>
                        </Col>

                        <Col span={12} style={{textAlign: 'right'}}>
                            <span>{bankName}</span>
                        </Col>
                    </Row>
                </div>

                <div style={{margin: '0px 0px 40px 0px'}}>
                    <div style={{margin: '0px 0px 10px 0px'}}>
                        <span style={{opacity: '45%', fontWeight: 'bold', margin: '0px 0px 15px 0px'}}>DuitNow</span>
                    </div>

                    <div>
                        <Image height={100} width={100} preview={false} src={duitNowQrImageUrl}/>
                    </div>
                </div>
            </div>
        </>
    )
}

export default PaymentDetailsCard;