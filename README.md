# atipera_zadanie

## How to run
Inside project directory run:
```
quarkus dev
```

## Endpoint: /repos/{user}
This endpoint retrieves a list of repositories for a given GitHub username.

### Method: GET
```
Path: /repos/{user}
```
### Path Parameter:

**{user}**: The GitHub username of the user whose repositories you want to retrieve. This is a required path parameter.

### Request:

No request body is needed.

### Successful Response (200 OK):

Returns a JSON array of repository objects. Each repository object has the following structure:
```json
[
  {
    "name": "repository_name",
    "owner": {
      "login": "username"
    },
    "branches": [
      {
        "name": "branch_name",
        "commit": {
          "sha": "commit_sha"
        }
      }
    ],
    "fork": true|false, // should always be false
  }
]
```
### Error Responses:

The API can return the following error responses:

**403 Forbidden**: Returned if the action is not permitted (e.g., due to rate limiting, authentication issues, or accessing private repositories without authorization). The response body should include a JSON object with:
```json
{
    "status": 403,
    "message": "Action not permitted. Details: {More specific error information}"
}
```

**404 Not Found**: Returned if the specified GitHub user is not found. The response body should include a JSON object with:
```json
{
    "status": 404,
    "message": "User not found"
}
```

**500 Internal Server Error**: Returned if an unexpected error occurs on the server-side (e.g., network issues while connecting to the GitHub API). The response body should include:
```json
{
  "status": 500,
  "message": "Internal Server Error.  Details: {More specific error information if possible}" 
}
```