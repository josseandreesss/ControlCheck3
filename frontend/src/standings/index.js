import React, { useEffect, useState } from "react";
import { Table } from "reactstrap";

export default function StandingsListing(props) {

    const [ratings, setRatings] = useState([]);
    const id = props.seasonId;

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await fetch(`/api/v1/matches`);
                const data = await res.json();
                setRatings(data);
            } catch (err) {
                console.log("Error", err);
            }
        }
        fetchData();
    }, [id]);

    if(ratings.length === 0) {
        return <h1>NOT ENOUGH MATCHES</h1>
    }

    return (
        <div>
            <Table>
                <thead>
                    <tr>
                        <th>Player</th>
                        <th>ELO</th>
                        <th>Wins</th>
                        <th>Losses</th>
                        <th>Draws</th>
                        <th>Win %</th>
                    </tr>
                </thead>
                <tbody>
                    {ratings.map((rating) => (
                        <tr key = {rating.id}>
                            <td>{rating.player?.username}</td>
                            <td>{rating.elo}</td>
                            <td>{rating.wins}</td>
                            <td>{rating.losses}</td>
                            <td>{rating.draws}</td>
                            <td>{rating.winRate}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    )
};