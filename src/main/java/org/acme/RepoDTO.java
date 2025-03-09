package org.acme;

import java.util.List;

public class RepoDTO {
    private String name;
    private Owner owner;
    private List<Branch> branches;
    private boolean fork;

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }
    public List<Branch> getBranches() { return this.branches; }
    public void setBranches(List<Branch> branches) { this.branches = branches; }
    public boolean isFork() { return this.fork; }
    public void setFork(String fork) { this.fork = Boolean.parseBoolean(fork); }

    public RepoDTO() {}

    public static class Owner {
        private String login;

        public String getLogin() { return login; }
        public void setLogin(String login) { this.login = login; }

        public Owner() {}
    }

    public static class Branch {
        private String name;
        private Commit commit;

        public String getName() { return this.name; }
        public Commit getCommit() { return this.commit; }
        public void setCommit(Commit commit) { this.commit = commit; }

        public Branch() {}

        public static class Commit {
            private String sha;

            public void setSha(String sha) { this.sha = sha; }
            public String getSha() { return this.sha; }

            public Commit() {}
        }
    }
}
