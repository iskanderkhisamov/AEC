import Radar from "react-d3-radar";
import React from "react";
import axios from "axios";

export default class ReactRadar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {person: "well"};
    }

    componentDidMount() {
        axios.get('https://kstu-aec.herokuapp.com/statistics/tupak').then(res => {
                const person = res.data.name;
                console.log(person);
                this.setState({ person });
                console.log(this.state.person);
            })
    }

    render() {
        return (
            <Radar
                width={200}
                height={200}
                padding={20}
                domainMax={10}
                highlighted={null}
                onHover={(point) => {
                    if (point) {
                        console.log('hovered over a data point');
                    } else {
                        console.log('not over anything');
                    }
                }}
                data={{
                    variables: [
                        {key: 'stats', label: this.state.person},
                        {key: 'strength', label: 'Strength'},
                        {key: 'adaptability', label: 'Adaptability'},
                        {key: 'creativity', label: 'Creativity'},
                        {key: 'openness', label: 'Open to Change'},
                        {key: 'confidence', label: 'Confidence'},
                    ],
                    sets: [
                        {
                            key: 'me',
                            label: 'My Scores',
                            values: {
                                stats: 5,
                                strength: 6,
                                adaptability: 7,
                                creativity: 2,
                                openness: 8,
                                confidence: 1,
                            },
                        },
                        {
                            key: 'everyone',
                            label: 'Everyone',
                            values: {
                                resilience: 10,
                                strength: 8,
                                adaptability: 6,
                                creativity: 4,
                                openness: 2,
                                confidence: 0,
                            },
                        },
                    ],
                }}
            />
        );
    }
}