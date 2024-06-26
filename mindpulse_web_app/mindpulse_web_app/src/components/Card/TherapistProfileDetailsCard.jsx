import {Divider, Tag} from "antd";

const TherapistProfileDetailsCard = () => {
    const divStyle = {
        display: 'flex',
        flexDirection: 'column',
        width: '863px'
    }

    const languageTagStyle = {
        backgroundColor: '#E6FFFB',
        color: '#13C2C2',
        borderColor: '#87E8DE',
        fontSize: '12px'
    }

    const unorderedListStyle = {
        fontSize: '12px',
    }

    const listStyle = {
        margin: '0px 0px 5px 0px'
    }

    const leftDivStyle = {
        width: '343px'
    }

    const rightDivStyle = {
        width: '520px'
    }

    const bottomDivStyle = {
        display: 'flex',
        flexDirection: 'row'
    }

    const iCanHelpWithUnorderedListDivStyle = {
        display: 'flex',
        flexDirection: 'row'
    }

    return(
        <div style={divStyle}>
            <span style={{fontWeight: 'bold', fontSize: '32px'}}>Kassandra</span>
            <Divider style={{margin: '13px 0px 12px 0px'}} />
            <div style={bottomDivStyle}>
                <div style={leftDivStyle}>
                    <div style={{margin: '0px 0px 20px 0px'}}>
                        <span style={{
                            margin: '0px 18px 0px 0px',
                            fontWeight: 'bold',
                            fontSize: '14px'
                        }}>Therapist Type:</span>
                        <span style={{fontSize: '14px'}}>Trainee</span>
                    </div>

                    <div style={{margin: '0px 0px 20px 0px'}}>
                        <span
                            style={{margin: '0px 55px 0px 0px', fontWeight: 'bold', fontSize: '14px'}}>Language:</span>
                        <Tag style={languageTagStyle}>English</Tag>
                        <Tag style={languageTagStyle}>Chinese</Tag>
                        <Tag style={languageTagStyle}>Malay</Tag>
                    </div>

                    <div>
                        <span style={{fontWeight: 'bold', fontSize: '14px'}}>Approaches:</span>
                        <ul style={unorderedListStyle}>
                            <li style={listStyle}>Trauma-Focused Cognitive Behavioral Therapy (TF-CBT)</li>
                            <li style={listStyle}>Eye Movement Desensitization and Reprocessing (EMDR)</li>
                            <li style={listStyle}>Solution-Focused Brief Therapy (SFBT)</li>
                            <li style={listStyle}>Acceptance and Commitment Therapy (ACT)</li>
                            <li style={listStyle}>Person-Centered Therapy (PCT)</li>
                            <li style={listStyle}>Expressive Art Therapy</li>
                        </ul>
                    </div>
                </div>


                <div style={rightDivStyle}>
                    <span style={{fontWeight: 'bold', fontSize: '14px'}}>I can help with:</span>
                    <div style={iCanHelpWithUnorderedListDivStyle}>
                        <ul style={unorderedListStyle}>
                            <li style={listStyle}>Abuse</li>
                            <li style={listStyle}>Academic</li>
                            <li style={listStyle}>Anger</li>
                            <li style={listStyle}>Anxiety</li>
                            <li style={listStyle}>Attention</li>
                            <li style={listStyle}>Deficit Hyperactivity Disorder (ADHD)</li>
                            <li style={listStyle}>Bipolar disorder</li>
                            <li style={listStyle}>Borderline personality disorder</li>
                            <li style={listStyle}>Burnout</li>
                            <li style={listStyle}>Career</li>
                            <li style={listStyle}>Depression</li>
                            <li style={listStyle}>Eating disorders</li>
                            <li style={listStyle}>Family</li>
                            <li style={listStyle}>Grief</li>
                            <li style={listStyle}>Interpersonal issues</li>
                        </ul>

                        <ul style={unorderedListStyle}>
                            <li style={listStyle}>Major Depressive Disorder (OCD)</li>
                            <li style={listStyle}>Panic</li>
                            <li style={listStyle}>Post-Traumatic Stress Disorder (PTSD)</li>
                            <li style={listStyle}>Career</li>
                            <li style={listStyle}>Relationship</li>
                            <li style={listStyle}>Self-development</li>
                            <li style={listStyle}>Stress</li>
                            <li style={listStyle}>Trauma</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default TherapistProfileDetailsCard
