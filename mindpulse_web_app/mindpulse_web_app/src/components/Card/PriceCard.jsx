import React from 'react';
import { Row, Col, Space } from 'antd';

const formLabelSpanStyle = {
    fontSize: '16px',
    fontWeight: 'bold',
}

const PriceCard = ({session, mode, s1, s2, taxRate, tax, totalPrice}) => {
    return (
        <Space direction="vertical" size="small" style={{display: 'flex', height: '110px'}}>
            <span style={formLabelSpanStyle}>Price</span>
            <div style={{margin: '0px 0px 51px 30px'}}>
                <Space direction="vertical" size="small" style={{ display: 'flex' }}>
                    {session === "firstSession" && (
                        <Row>
                            <Col span={12}>
                                <span style={{ fontSize: '12px' }}>Mental Health Screening:</span>
                            </Col>
                            <Col span={12}>
                                <span style={{ fontSize: '12px' }}>RM {s1[mode === "online" ? "OnlineRate" : "InPersonRate"].toFixed(2)}</span>
                            </Col>
                        </Row>
                    )}
                    <Row>
                        <Col span={12}>
                            <span style={{ fontSize: '12px' }}>Individual Therapy:</span>
                        </Col>
                        <Col span={12}>
                            <span style={{ fontSize: '12px' }}>RM {s2[mode === "online" ? "OnlineRate" : "InPersonRate"].toFixed(2)}</span>
                        </Col>
                    </Row>
                    <Row>
                        <Col span={12}>
                            <span style={{ fontSize: '12px' }}>Tax ({(taxRate * 100).toFixed(0)}%):</span>
                        </Col>
                        <Col span={12}>
                            <span style={{ fontSize: '12px' }}>RM {tax.toFixed(2)} </span>
                        </Col>
                    </Row>
                    <Row>
                        <Col span={12}>
                            <span style={{ fontSize: '14px', fontWeight: 'bold' }}>Total Price:</span>
                        </Col>
                        <Col span={12}>
                            <span style={{ fontSize: '14px', fontWeight: 'bold' }}>RM {totalPrice.toFixed(2)}</span>
                        </Col>
                    </Row>
                </Space>
            </div>
        </Space>
    );
};

export default PriceCard;
