import './main.css';
import './normalize.css';
import React from 'react';
import Chart from "react-apexcharts";
import ReactFlow from 'react-flow-renderer';
import axios from "axios";

class ApexChart extends React.Component {
    constructor(props) {
        super(props);
        console.log(this.props.pol, this.props.chl, this.props.upr);
        this.state = {
            series: [{
                name: 'Series 1',
                data: [this.props.pol, this.props.chl, this.props.upr],
            }],
            options: {
                chart: {
                    height: 350,
                    type: 'radar',
                    dropShadow: {
                        enabled: true,
                        blur: 1,
                        left: 1,
                        top: 1
                    },
                    toolbar: {
                        show: false
                    }
                },
                title: {
                    text: this.props.name,
                    align: 'center',
                    style: {
                        color: 'black'
                    }
                },
                stroke: {
                    show: true,
                    width: 2,
                    colors: [],
                    dashArray: 0
                },
                fill: {
                    opacity: 0.5
                },
                markers: {
                    size: 5,
                    hover: {
                        size: 10
                    }
                },
                xaxis: {
                    categories: ['POL', 'CHL', 'UPR'],
                    labels: {
                        style: {
                            colors: ['red', 'green', 'blue']
                        }
                    }
                },
                yaxis: {
                    tickAmount: 5,
                    min: 0,
                    max: 100,
                    labels: {
                        style: {
                            colors: 'black'
                        }
                    }
                }
            },
        };
    }

    render() {
        return (
            <div className="app">
                <div className="row">
                    <div className="mixed-chart">
                        <Chart
                            options={this.state.options}
                            series={this.state.series}
                            type="radar"
                            width="500"
                        />
                    </div>
                </div>
            </div>
        );
    }
}

class RGraph extends React.Component{
    render() {
        const elements = [
            { id: "1",
                style: {background: "rgba(0,0,0,0)", width: 480, height: 250, border: 'none'},
                data: {
                    label: <ApexChart name={this.props.name}
                                      upr={this.props.upr}
                                      chl={this.props.chl}
                                      pol={this.props.pol}/>
                },
                position: { x: 100, y: 550 }
            }
        ];

        const graphStyles = { width: "100%", height: "1080px", backgroundColor: "#ffffff"};

        const BasicGraph = () => <ReactFlow elements={elements} style={graphStyles} nodesConnectable={false}/>;

        return <BasicGraph />;
    }
}

class App extends React.Component {
    state = {
        user
    }

    getUser = () => {
        axios
            .get("http://localhost:8080/statistics/user")
            .then(data => this.setState({ user: data.data }))
            .catch(err => {
                console.log(err);
                return null;
            });
    };

    help = () => {
        axios
            .get("http://localhost:8080/statistics/help")
            .then(data => this.setState({ help: data.data }))
            .catch(err => {
                console.log(err);
                return null;
            });
    };

    componentDidMount() {
        this.getUser();
        this.help();
    }

    render() {
        let styleConfig = { color: "#86C232"}
        console.log('name = ', this.state.user.fullname,'pol = ', this.state.user.pol,' chl = ', this.state.user.chl,' upr = ', this.state.user.upr,' help = ', this.state.help);
        return (
            <div className="wrapper">
                <header className="header">
                    <div className="header__body responsive-wrapper">
                        <div className="header__name"><a href="http://localhost:8080/">Автоматизированный учебный курс</a></div>
                        <nav className="header__nav">
                            <a href="http://localhost:8080/profile">Личный кабинет</a>
                        </nav>
                    </div>
                </header>
                <main className="main">
                    <div className="main__body responsive-wrapper">
                        <div className="graphic">
                            {this.state.name.length === 1 && this.state.upr === -1 && this.state.pol === -1
                            && this.state.chl === -1 && this.state.help === -1 ? (<div>Loading...</div>) : (<RGraph name={this.state.name}
                            upr={this.state.upr} chl={this.state.chl} pol={this.state.pol}/>)}
                        </div>
                    </div>
                </main>
            </div>
        );
    }
}

export default App;