import { render, screen } from "../test-utils.jsx";
import { mockStandings } from "../mocks/handlers.js";
import {within } from '@testing-library/react'
import MatchesListing from "./index.js";
import StandingsListing from "./index.js";

const getDataRows = (table) => {
    const allRows = within(table).getAllByRole("row");
    console.log("All rows count:", allRows.length);
    var dataRows=allRows.filter(
      (row) => within(row).queryAllByRole("columnheader").length === 0
    );
    console.log("Filterd rows count:", allRows.length);
    return dataRows;
  };

describe('Basic listing of standings', () => {
        test('renders a table with data rows from the API (as many rows as mock data available)', async () => {
        render(<StandingsListing seasonId={2}/>);

        // Wait until the table is in the DOM
        const table = await screen.findByRole("table");

       // Get all <tr> elements (no assumption about <tbody>)
        const rows = within(table).getAllByRole("row");
        // Filter out header rows — those containing “Player” or “Wins”
        const dataRows =  getDataRows(table);            
        // Should have one row per match in mock data
        expect(dataRows.length).toBe(mockStandings.length)            
        
        
    });

     test("renders a table with the data of the first mock standing", async () => {
        render(<StandingsListing seasonId={2} />);        
       // Wait until the table is in the DOM
        const table = await screen.findByRole("table");

        // Get all <tr> elements (no assumption about <tbody>)
        const allRows = within(table).getAllByRole("row");

        // Filter out header rows — those containing “Player” or “Wins”
        const dataRows =  getDataRows(table);

        // Should have one row per rating in mock data
        expect(dataRows.length).toBe(mockStandings.length);

        // Verify first row (basic data presence)
        const firstRow = dataRows[0];
        const cells = within(firstRow).getAllByRole("cell");        
        // 0 Player, 1 ELO, 2 Wins, 3 Losses, 4 Draws, 5 Win%
        expect(cells[0]).toHaveTextContent(mockStandings[0].player.username);
        expect(cells[1]).toHaveTextContent(mockStandings[0].elo); 
        expect(cells[2]).toHaveTextContent(mockStandings[0].wins);        
        expect(cells[3]).toHaveTextContent(mockStandings[0].losses);
        expect(cells[4]).toHaveTextContent(mockStandings[0].draws); 
        expect(cells[5]).toHaveTextContent(mockStandings[0].winRate);        
    });

    test("renders a table with the data of the last mock standing", async () => {
        render(<StandingsListing seasonId={2} />);        
       // Wait until the table is in the DOM
        const table = await screen.findByRole("table");

        // Get all <tr> elements (no assumption about <tbody>)
        const allRows = within(table).getAllByRole("row");

        // Filter out header rows — those containing “Player” or “Wins”
        const dataRows = getDataRows(table);

        // Should have one row per rating in mock data
        expect(dataRows.length).toBe(mockStandings.length);

        // Verify second row (lower elo)
        const secondRow = dataRows[1];
        // Get all <td> cells in order
        const cells = within(secondRow).getAllByRole("cell");
        // 0 Player, 1 ELO, 2 Wins, 3 Losses, 4 Draws, 5 Win%
        expect(cells[0]).toHaveTextContent(mockStandings[1].player.username);
        expect(cells[1]).toHaveTextContent(mockStandings[1].elo); 
        expect(cells[2]).toHaveTextContent(mockStandings[1].wins);        
        expect(cells[3]).toHaveTextContent(mockStandings[1].losses);
        expect(cells[4]).toHaveTextContent(mockStandings[1].draws); 
        expect(cells[5]).toHaveTextContent(mockStandings[1].winRate); 
    });
});